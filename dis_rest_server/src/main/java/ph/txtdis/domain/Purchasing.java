package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Purchasing extends NumberedOrder {

	private static final long serialVersionUID = -5606817850562768621L;

	@ManyToOne
	@JoinColumn(name = "sent_for_approval_by")
	private User sentForApprovalBy;

	@Column(name = "sent_for_approval_on")
	private ZonedDateTime sentForApprovalOn;

	private Boolean approved;

	@ManyToOne
	@JoinColumn(name = "decided_by")
	private User decidedBy;

	@Column(name = "decided_on")
	private ZonedDateTime decidedOn;

	@ManyToOne
	@JoinColumn(name = "sent_to_vendor_by")
	private User sentToVendorBy;

	@Column(name = "sent_to_vendor_on")
	private ZonedDateTime sentToVendorOn;

	@ManyToOne
	@JoinColumn(name = "items_received_by")
	private User itemsReceivedBy;

	@Column(name = "items_received_on")
	private ZonedDateTime itemsReceivedOn;

	@JoinColumn(name = "purchasing_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<SoldDetail> details;

	public Purchasing(Customer customer, LocalDate orderDate) {
		this.customer = customer;
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "P/O No. " + getId();
	}
}
