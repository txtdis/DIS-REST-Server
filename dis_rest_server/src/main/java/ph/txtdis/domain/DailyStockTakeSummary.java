package ph.txtdis.domain;

import java.time.LocalDate;
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
public class DailyStockTakeSummary extends AbstractDated {

	private static final long serialVersionUID = 8555439339502494765L;

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

	public DailyStockTakeSummary(User cutoffBy, LocalDate id) {
		setCutoffBy(cutoffBy);
		setId(id);
	}
}
