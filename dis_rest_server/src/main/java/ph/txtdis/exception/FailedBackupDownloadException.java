package ph.txtdis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedBackupDownloadException extends Exception {

	private static final long serialVersionUID = -2016039633987234867L;

	public FailedBackupDownloadException() {
		super("Backup download failed;\nCheck internet connection,\nthen reboot server.");
	}
}
