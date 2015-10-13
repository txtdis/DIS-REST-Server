
package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractDeactivatedName extends AbstractName {

	private static final long serialVersionUID = 6409589135828964023L;

	protected String deactivatedBy;

	protected ZonedDateTime deactivatedOn;

	public AbstractDeactivatedName(String name) {
		super(name);
	}
}