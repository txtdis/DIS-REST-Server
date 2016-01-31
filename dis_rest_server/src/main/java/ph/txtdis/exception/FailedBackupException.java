package ph.txtdis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class FailedBackupException extends Exception {

	private static final long serialVersionUID = -5045178961218999208L;

	public FailedBackupException() {
		super("Backup failed;\nCheck server, network and backup media,\nthen retry.");
	}
}
