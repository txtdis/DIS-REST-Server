package ph.txtdis.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.dto.Backup;
import ph.txtdis.exception.FailedBackupException;
import ph.txtdis.exception.FailedDownloadException;
import ph.txtdis.exception.FailedReplicationException;
import ph.txtdis.exception.FailedUploadException;
import ph.txtdis.exception.NoNewerBackupException;
import ph.txtdis.service.BackupService;
import ph.txtdis.service.DownloadService;
import ph.txtdis.service.RestoreService;
import ph.txtdis.service.UploadService;

@RequestMapping("/backups")
@RestController("syncController")
public class SyncController {

	@Autowired
	private DownloadService download;

	@Autowired
	private BackupService backup;

	@Autowired
	private UploadService upload;

	@Autowired
	private RestoreService restore;

	@RequestMapping(path = "/inBytes", method = GET)
	public ResponseEntity<?> get() throws FailedBackupException {
		Backup b = new Backup(backup.getBackupBytes());
		return new ResponseEntity<Backup>(b, OK);
	}

	@RequestMapping(path = "/restoreBackup", method = GET)
	public ResponseEntity<?> restoreBackup(Date d)
			throws FailedDownloadException, NoNewerBackupException, FailedReplicationException {
		download.downloadBackup(d);
		restore.restore();
		Backup b = new Backup("Sync Successful");
		return new ResponseEntity<Backup>(b, OK);
	}

	@RequestMapping(path = "/uploadBackup", method = GET)
	public ResponseEntity<?> uploadBackup() throws FailedBackupException, FailedUploadException {
		backup.backup();
		upload.uploadBackup();
		Backup b = new Backup("Upload Successful");
		return new ResponseEntity<Backup>(b, OK);
	}
}