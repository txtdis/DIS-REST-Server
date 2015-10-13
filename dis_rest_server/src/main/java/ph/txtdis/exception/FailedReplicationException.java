package ph.txtdis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedReplicationException extends Exception {

	private static final long serialVersionUID = -3873340228411218001L;

	public FailedReplicationException() {
		super("Replication failed;\nCheck server, internet connection,\nthen reboot server.");
	}
}
