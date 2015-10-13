package ph.txtdis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedBackupUploadException extends Exception {

	private static final long serialVersionUID = 4529227362222601552L;

	public FailedBackupUploadException() {
		super("Backup upload failed;\nCheck internet connection,\nthen reboot server.");
	}
}
