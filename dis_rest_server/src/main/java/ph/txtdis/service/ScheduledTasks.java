package ph.txtdis.service;

import static ph.txtdis.type.SyncType.BACKUP;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static ph.txtdis.util.DateTimeUtils.epochDate;

import ph.txtdis.domain.Sync;
import ph.txtdis.exception.FailedReplicationException;
import ph.txtdis.repository.SyncRepository;
import ph.txtdis.type.SyncType;

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

	@Autowired
	private ServerService server;

	@Autowired
	private SyncRepository repository;

	private Date date;

	private boolean downloadedBackup, madeBackup, uploadedBackup, restoredBackup, updatedPostDatedChecks,
			valuatedInventories, deactivatedNonBuyingOutlets, deactivatedNonMovingItems;

	@Scheduled(cron = "0 30 9/1 * * MON-SAT")
	public void reretrieveOnFailures() {
		if (server.isSlave())
			// retrieveBackup()
			;
	}

	@Scheduled(cron = "0 0 9/1 * * MON-SAT")
	public void resendOnFailures() {
		if (server.isMaster())
			sendBackup();
	}

	@Scheduled(cron = "0 30 8 * * MON-SAT")
	public void retrieveBackup() {
		if (server.isSlave())
			// restoreFromMailedBackup()
			;
	}

	@Scheduled(cron = "0 0 8 * * MON-SAT")
	public void sendBackup() {
		if (server.isMaster())
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
			if (downloadedBackup)
				return;
			date = downloadService.download(BACKUP, date);
			downloadedBackup = true;
		} catch (Exception e) {
			e.printStackTrace();
			downloadedBackup = false;
		}
	}

	private void mailUpdatedBackup() {
		setLatestBackupDate();
		updateData();
		backupServer();
		uploadBackup();
	}

	private void restoreBackup() {
		try {
			if (!restoredBackup) {
				restoreService.restoreFromDownloadedBackup();
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

	private void setLatestBackupDate() {
		// TODO Auto-generated method stub
		Sync s = repository.findFirstByTypeOrderByLastSyncDesc(SyncType.BACKUP);
		date = (s == null ? epochDate() : s.getLastSync());
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
				uploadService.upload("backup");
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
