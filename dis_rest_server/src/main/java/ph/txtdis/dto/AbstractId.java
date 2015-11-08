package ph.txtdis.dto;

import lombok.Data;
import ph.txtdis.domain.Keyed;

@Data
public abstract class AbstractId<PK> implements Keyed<PK> {

	private PK id;
}