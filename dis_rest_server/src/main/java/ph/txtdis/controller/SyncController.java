package ph.txtdis.controller;

import static java.time.LocalDate.now;
import static org.apache.log4j.Logger.getLogger;
import static org.springframework.http.HttpStatus.OK;
import static ph.txtdis.type.SyncType.BACKUP;
import static ph.txtdis.type.SyncType.SCRIPT;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ph.txtdis.util.DateTimeUtils.epochDate;
import static ph.txtdis.util.DateTimeUtils.toUtilDate;

import ph.txtdis.domain.Script;
import ph.txtdis.domain.Sync;
import ph.txtdis.exception.FailedReplicationException;
import ph.txtdis.exception.NoNewerFileException;
import ph.txtdis.repository.SyncRepository;
import ph.txtdis.service.BackupService;
import ph.txtdis.service.DownloadService;
import ph.txtdis.service.RestoreService;
import ph.txtdis.service.ScriptService;
import ph.txtdis.service.ServerService;
import ph.txtdis.service.UploadService;

@RequestMapping("/syncs")
@RestController("syncController")
public class SyncController {

	private static final String SUCCESSFUL = "successful";

	private static final String UPDATE_SUCCESSFUL = "Update " + SUCCESSFUL;

	private static final String REPLICATION_SUCCESSFUL = "Replication " + SUCCESSFUL;

	private static final String UPLOAD_SUCCESSFUL = "Upload " + SUCCESSFUL;

	private static Logger logger = getLogger(SyncController.class);

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private BackupService backupService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private RestoreService restoreService;

	@Autowired
	private ServerService serverService;

	@Autowired
	private ScriptService scriptService;

	@Autowired
	private SyncRepository syncRepository;

	private List<Script> scripts;

	@RequestMapping(path = "/download")
	public ResponseEntity<?> download()
			throws FailedReplicationException, NoNewerFileException, FailedReplicationException {
		logger.info("Server is a slave = " + serverService.isSlave());
		String s = "Nothing to download";
		if (serverService.isSlave())
			s = restoreBackup();
		else
			s = runScripts();
		return new ResponseEntity<>(s, OK);
	}

	@RequestMapping(path = "/upload")
	public ResponseEntity<?> upload() throws FailedReplicationException {
		logger.info("Server is a master = " + serverService.isMaster());
		String s = "Nothing to upload";
		if (serverService.isMaster())
			s = uploadBackup();
		else if (scriptsExist())
			s = uploadScript();
		return new ResponseEntity<>(s, OK);
	}

	private Sync getLatestSync() {
		Sync sync = syncRepository.findFirstByTypeOrderByLastSyncDesc(SCRIPT);
		return sync != null ? sync : new Sync(SCRIPT, epochDate());
	}

	private String restoreBackup() throws NoNewerFileException, FailedReplicationException {
		downloadService.download(BACKUP, toUtilDate(now()));
		restoreService.restoreFromDownloadedBackup();
		return REPLICATION_SUCCESSFUL;
	}

	private String runScripts() throws NoNewerFileException, FailedReplicationException {
		Sync sync = getLatestSync();
		Date latest = sync.getLastSync();
		latest = downloadService.download(SCRIPT, latest);
		scriptService.runDownloadedScripts();

		sync.setLastSync(latest);
		syncRepository.save(sync);
		return UPDATE_SUCCESSFUL;
	}

	private boolean scriptsExist() {
		scripts = scriptService.listScripts();
		logger.info("Scripts = " + scripts);
		return scripts != null;
	}

	private String uploadBackup() throws FailedReplicationException {
		backupService.backup();
		uploadService.upload("backup");
		return UPLOAD_SUCCESSFUL;
	}

	private String uploadScript() throws FailedReplicationException {
		scriptService.write(scripts);
		uploadService.upload("script");
		scriptService.tagSentScriptsAsSuch(scripts);
		return UPLOAD_SUCCESSFUL;
	}
}