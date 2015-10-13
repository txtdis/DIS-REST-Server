package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceSettlement extends AuditedId {

	private static final long serialVersionUID = 3725295775807671330L;

	@ManyToOne
	private Truck truck;

	@ManyToOne
	private User reconciledBy;

	private ZonedDateTime reconciledOn;

	public RemittanceSettlement(Truck truck) {
		this.truck = truck;
	}
}
