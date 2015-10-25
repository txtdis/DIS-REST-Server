package ph.txtdis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedPrintingException extends Exception {

	private static final long serialVersionUID = -5399480827294368809L;

	public FailedPrintingException(String message) {
		super("Printing failed:\n" + message);
	}
}
