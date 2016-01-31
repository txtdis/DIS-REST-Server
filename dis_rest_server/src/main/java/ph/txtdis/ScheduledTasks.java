package ph.txtdis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedReplicationException;
import ph.txtdis.service.BackupService;
import ph.txtdis.service.DownloadService;
import ph.txtdis.service.PostDatedChequeService;
import ph.txtdis.service.RestoreService;
import ph.txtdis.service.UploadService;

@Component("scheduledTasks")
public class ScheduledTasks {

	@Autowired
	private BackupService backupService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private PostDatedChequeService postDatedChequeService;

	@Autowired
	private RestoreService restoreService;

	@Value("${replication}")
	private String replication;

	private boolean downloadedBackup, madeBackup, uploadedBackup, restoredBackup, updatedPostDatedChecks,
			valuatedInventories, deactivatedNonBuyingOutlets, deactivatedNonMovingItems;

	@Scheduled(cron = "0 30 9/1 * * MON-SAT")
	public void reretrieveOnFailures() {
		if (isSlave())
			retrieveBackup();
	}

	@Scheduled(cron = "0 0 9/1 * * MON-SAT")
	public void resendOnFailures() {
		if (isMaster())
			sendBackup();
	}

	@Scheduled(cron = "0 30 8 * * MON-SAT")
	public void retrieveBackup() {
		if (isSlave())
			restoreFromMailedBackup();
	}

	@Scheduled(cron = "0 0 8 * * MON-SAT")
	public void sendBackup() {
		if (isMaster())
			mailUpdatedBackup();
	}

	private void backupServer() {
		try {
			if (!madeBackup) {
				backupService.backup();
				madeBackup = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			madeBackup = false;
		}
	}

	private void deactivateNonBuyingOutlets() {
		if (!deactivatedNonBuyingOutlets) {
			// TODO
			deactivatedNonBuyingOutlets = true;
		}
	}

	private void deactivateNonMovingItems() {
		if (!deactivatedNonMovingItems) {
			// TODO
			deactivatedNonMovingItems = true;
		}
	}

	private void downloadBackup() {
		try {
			if (!downloadedBackup) {
				downloadService.downloadBackup(new Date());
				downloadedBackup = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			downloadedBackup = false;
		}
	}

	private boolean isMaster() {
		return replication.equals("master");
	}

	private boolean isSlave() {
		return !isMaster();
	}

	private void mailUpdatedBackup() {
		updateData();
		backupServer();
		uploadBackup();
	}

	private void restoreBackup() {
		try {
			if (!restoredBackup) {
				restoreService.restore();
				restoredBackup = true;
			}
		} catch (FailedReplicationException e) {
			e.printStackTrace();
			restoredBackup = false;
		}
	}

	private void restoreFromMailedBackup() {
		downloadBackup();
		restoreBackup();
	}

	private void updateData() {
		// TODO Auto-generated method stub
		deactivateNonBuyingOutlets();
		deactivateNonMovingItems();
		updatePostDatedChecks();
		valuateInventory();
	}

	private void updatePostDatedChecks() {
		if (!updatedPostDatedChecks) {
			postDatedChequeService.setFullyPaidForMaturedPostDatedChecks();
			updatedPostDatedChecks = true;
		}
	}

	private void uploadBackup() {
		try {
			if (!uploadedBackup) {
				uploadService.uploadBackup();
				uploadedBackup = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			uploadedBackup = false;
		}
	}

	private void valuateInventory() {
		if (!valuatedInventories) {
			// TODO
			valuatedInventories = true;
		}
	}
}
