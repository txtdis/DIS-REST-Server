package ph.txtdis.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.type.SyncType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sync implements Serializable, Keyed<SyncType> {

	private static final long serialVersionUID = 4934707118618469477L;

	@Id
	private SyncType type;

	@Column(name = "last_sync")
	private Date lastSync;

	@Override
	public SyncType getId() {
		return type;
	}
}
