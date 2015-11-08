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
public abstract class AuditedOrder extends TrackedOrder {

	private static final long serialVersionUID = 8939708088215882688L;

	@Column(name = "is_valid")
	private Boolean isValid;

	@Column(name = "audited_by")
	private String auditedBy;

	@Column(name = "audited_on")
	private ZonedDateTime auditedOn;
}