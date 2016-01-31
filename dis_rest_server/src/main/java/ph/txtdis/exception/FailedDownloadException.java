package ph.txtdis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class FailedDownloadException extends Exception {

	private static final long serialVersionUID = -2016039633987234867L;

	public FailedDownloadException() {
		super("Download failed;\nCheck internet connection,\nthen reboot server.");
	}
}