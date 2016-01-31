package ph.txtdis.remote;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.CreationTracked;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Remote extends CreationTracked<Long> {

	private static final long serialVersionUID = 3784979816389961075L;

	private TableType type;

	private ActionType action;

	private String data;
}
