package ph.txtdis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class FailedUploadException extends Exception {

	private static final long serialVersionUID = 4529227362222601552L;

	public FailedUploadException() {
		super("Upload failed;\nCheck internet connection,\nthen reboot server.");
	}
}
