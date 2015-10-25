package ph.txtdis.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "load_settlement")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadSettlement extends TrackedOrder {

	private static final long serialVersionUID = 6076045230689493543L;

	@ManyToOne
	private Truck truck;

	@ManyToOne
	@JoinColumn(name = "reconciled_by")
	private User reconciledBy;

	@Column(name = "reconciled_on")
	private ZonedDateTime reconciledOn;

	public LoadSettlement(Truck truck) {
		this.truck = truck;
	}
}
