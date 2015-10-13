package ph.txtdis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.dto.Backup;
import ph.txtdis.exception.FailedBackupException;
import ph.txtdis.sync.BackupService;

@RestController("backupController")
@RequestMapping("/backups")
public class BackupController {

	@Autowired
	private BackupService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> backup() throws FailedBackupException {
		Backup file = new Backup(service.getBackupBytes());
		return new ResponseEntity<Backup>(file, HttpStatus.OK);
	}
}