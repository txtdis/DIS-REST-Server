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

import ph.txtdis.exception.FailedReplicationException;

@Service("restoreService")
public class RestoreService {

	private static Logger logger = Logger.getLogger(RestoreService.class);

	@Value("${database.version}")
	private String databaseVersion;

	@Value("${database.name}")
	private String databaseName;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public void restore() throws FailedReplicationException {
		try {
			startRestoring();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedReplicationException();
		}
	}

	private ProcessBuilder build() {
		ProcessBuilder pb = new ProcessBuilder(restoreCommand());
		pb.environment().put("PGPASSWORD", password);
		pb.redirectErrorStream(true);
		return pb;
	}

	private void logRestoreProcess(Process p) throws Exception {
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String log;
		while ((log = br.readLine()) != null)
			logger.info(log);
	}

	private String postgresRestoreAppPath() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows"))
			return "c:\\Program Files\\PostgreSQL\\" + databaseVersion + "\\bin\\pg_restore.exe";
		else if (os.contains("Mac"))
			return "/Library/PostgreSQL/" + databaseVersion + "/bin/pg_restore";
		return "pg_restore";
	}

	private List<String> restoreCommand() {
		// @formatter:off
		return Arrays.asList(
			postgresRestoreAppPath(),
			"--host=localhost",
			"--port=5432",
			"--username=" + username,
			"--no-password",
			"--clean",
			"--exit-on-error",
			"--single-transaction",
			"--verbose",
			"--dbname=" + databaseName,
			System.getProperty("user.home") + File.separator + databaseName + ".backup");
		// @formatter:on
	}

	private void startRestoring() throws Exception {
		Process p = build().start();
		logRestoreProcess(p);
		p.waitFor();
		if (p.exitValue() != 0)
			throw new FailedReplicationException();
	}
}
