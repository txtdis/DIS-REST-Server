
package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
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

	@Column(name = "deactivated_by")
	protected String deactivatedBy;

	@Column(name = "deactivated_on")
	protected ZonedDateTime deactivatedOn;

	public AbstractDeactivatedName(String name) {
		super(name);
	}
}