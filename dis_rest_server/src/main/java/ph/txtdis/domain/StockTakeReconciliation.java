package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "stock_take_reconciliation")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTakeReconciliation extends AbstractDated {

	private static final long serialVersionUID = -7137997478152209732L;

	@ManyToOne
	@JoinColumn(name = "cutoff_by")
	private User cutoffBy;

	@Column(name = "cutoff_on")
	private ZonedDateTime cutoffOn;

	@ManyToOne
	@JoinColumn(name = "closed_by")
	private User closedBy;

	@Column(name = "closed_on")
	private ZonedDateTime closedOn;

	@ManyToOne
	@JoinColumn(name = "reconciled_by")
	private User reconciledBy;

	@Column(name = "reconciled_on")
	private ZonedDateTime reconciledOn;

	@ManyToOne
	@JoinColumn(name = "mailed_by")
	private User mailedBy;

	@Column(name = "mailed_on")
	private ZonedDateTime mailedOn;

	@ManyToOne
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_on")
	private ZonedDateTime approvedOn;

	@Column(name = "is_approved")
	private boolean isApproved;

	@ManyToOne
	@JoinColumn(name = "completed_by")
	private User completedBy;

	@Column(name = "completed_on")
	private ZonedDateTime completedOn;

	public StockTakeReconciliation(User cutoffBy, LocalDate id) {
		setCutoffBy(cutoffBy);
		setId(id);
	}
}
