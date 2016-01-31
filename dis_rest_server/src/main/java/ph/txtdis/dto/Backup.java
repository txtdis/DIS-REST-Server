package ph.txtdis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Backup {

	private byte[] file;

	private String message;

	public Backup(byte[] file) {
		this.file = file;
	}

	public Backup(String message) {
		this.message = message;
	}
}
