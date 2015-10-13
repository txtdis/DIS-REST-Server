package ph.txtdis.domain;

import java.time.LocalDate;
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
public class StockTakeReconciliation extends AbstractDated {

	private static final long serialVersionUID = -7137997478152209732L;

	@ManyToOne
	private User cutoffBy;

	private ZonedDateTime cutoffOn;

	@ManyToOne
	private User closedBy;

	private ZonedDateTime closedOn;

	@ManyToOne
	private User reconciledBy;

	private ZonedDateTime reconciledOn;

	@ManyToOne
	private User mailedBy;

	private ZonedDateTime mailedOn;

	@ManyToOne
	private User approvedBy;

	private ZonedDateTime approvedOn;

	private boolean isApproved;

	@ManyToOne
	private User completedBy;

	private ZonedDateTime completedOn;

	public StockTakeReconciliation(User cutoffBy, LocalDate id) {
		setCutoffBy(cutoffBy);
		setId(id);
	}
}
