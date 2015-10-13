package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadSettlement extends AuditedId {

	private static final long serialVersionUID = 6076045230689493543L;

	@ManyToOne
	private Truck truck;

	@ManyToOne
	private User reconciledBy;

	private ZonedDateTime reconciledOn;

	public LoadSettlement(Truck truck) {
		this.truck = truck;
	}
}
