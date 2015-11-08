package ph.txtdis.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ph.txtdis.exception.FailedBackupException;
import ph.txtdis.util.BinaryUtils;

@Service("backupService")
public class BackupService {

	private static Logger logger = Logger.getLogger(BackupService.class);

	@Value("${database.version}")
	private String databaseVersion;

	@Value("${database.name}")
	private String databaseName;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	private String backup;

	public void backup() throws FailedBackupException {
		try {
			backup = System.getProperty("user.home") + File.separator + databaseName + ".backup";
			startBackup();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedBackupException();
		}
	}

	public byte[] getBackupBytes() throws FailedBackupException {
		try {
			backup();
			return BinaryUtils.toBytes(backup);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedBackupException();
		}
	}

	private List<String> backupCommand() {
		// @formatter:off
		return Arrays.asList(
            postgresAppPath(),
            "--host=localhost",
            "--port=5432",
            "--username=" + username,
            "--no-password",
            "--file=" + backup,
            "--format=custom",
            "--compress=9",
            "--verbose",
            "--dbname=" + databaseName);
        // @formatter:on
	}

	private ProcessBuilder buildProcess() {
		ProcessBuilder pb = new ProcessBuilder(backupCommand());
		pb.environment().put("PGPASSWORD", password);
		pb.redirectErrorStream(true);
		return pb;
	}

	private void logBackupProcess(Process p) throws Exception {
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String log;
		while ((log = br.readLine()) != null)
			logger.info(log);
	}

	private String postgresAppPath() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows"))
			return "c:\\Program Files\\PostgreSQL\\" + databaseVersion + "\\bin\\pg_dump.exe";
		else if (os.contains("Mac"))
			return "/Library/PostgreSQL/" + databaseVersion + "/bin/pg_dump";
		return "pg_dump";
	}

	private void startBackup() throws Exception {
		Process p = buildProcess().start();
		logBackupProcess(p);
		p.waitFor();
		if (p.exitValue() != 0)
			throw new FailedBackupException();
	}
}
