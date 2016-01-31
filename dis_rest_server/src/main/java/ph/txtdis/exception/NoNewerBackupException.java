package ph.txtdis.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Date;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class NoNewerBackupException extends Exception {

	private static final long serialVersionUID = 6996562319537406273L;

	public NoNewerBackupException(Date d) {
		super("There is no backup\nnewer than " + d.toString());
	}
}
