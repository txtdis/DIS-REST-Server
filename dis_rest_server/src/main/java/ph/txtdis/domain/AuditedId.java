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
public abstract class AuditedId extends TrackedId {

	private static final long serialVersionUID = 8939708088215882688L;

	protected String revisedBy;

	protected ZonedDateTime revisedOn;

	protected String invalidatedBy;

	protected ZonedDateTime invalidatedOn;

	protected String validatedBy;

	protected ZonedDateTime validatedOn;
}