package ph.txtdis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedBackupDownloadException;
import ph.txtdis.exception.FailedBackupException;
import ph.txtdis.exception.FailedBackupUploadException;
import ph.txtdis.exception.FailedReplicationException;
import ph.txtdis.sync.BackupDownloader;
import ph.txtdis.sync.BackupService;
import ph.txtdis.sync.BackupUploader;
import ph.txtdis.sync.RestoreService;

@Component("scheduledTasks")
public class ScheduledTasks {

	@Autowired
	private BackupService backupService;

	@Autowired
	private BackupUploader backupUploader;

	@Autowired
	private BackupDownloader backupDownloader;

	@Autowired
	private RestoreService restoreService;

	@Value("${replication}")
	private String replication;

	private boolean downloaded;

	@Scheduled(cron = "* * * * * MON-SAT")
	public void syncServers() {
		// if (replication.equals("master"))
		// sendReplica();
		// else
		// replicate();
	}

	private void backupServer() throws FailedBackupException {
		backupService.backup();
	}

	private void downloadBackup() throws FailedBackupDownloadException {
		backupDownloader.download(new Date());
	}

	private void replicate() {
		try {
			if (!downloaded)
				downloadBackup();
			restoreBackup();
		} catch (FailedBackupDownloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedReplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			downloaded = true;
		}
	}

	private void restoreBackup() throws FailedReplicationException {
		restoreService.restore();
	}

	private void sendReplica() {
		try {
			backupServer();
			uploadBackup();
		} catch (FailedBackupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedBackupUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uploadBackup() throws FailedBackupUploadException {
		backupUploader.upload();
	}
}
