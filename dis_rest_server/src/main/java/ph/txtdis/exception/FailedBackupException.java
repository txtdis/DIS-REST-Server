package ph.txtdis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedBackupException extends Exception {

	private static final long serialVersionUID = -5045178961218999208L;

	public FailedBackupException() {
		super("Backup failed;\nCheck server, network and backup media,\nthen retry.");
	}
}
