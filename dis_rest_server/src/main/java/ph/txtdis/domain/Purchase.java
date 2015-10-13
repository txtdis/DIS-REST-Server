package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Purchase extends NumberedOrder {

	private static final long serialVersionUID = -5606817850562768621L;

	@ManyToOne
	protected User deactivatedBy;

	protected ZonedDateTime deactivatedOn;

	@ManyToOne
	protected User mailedBy;

	protected ZonedDateTime mailedOn;

	@ManyToOne
	protected User sentBy;

	protected ZonedDateTime sentOn;

	@ManyToOne
	protected User receivedBy;

	protected ZonedDateTime receivedOn;

	@JoinColumn(name = "purchase_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<PurchaseDetail> details;

	public Purchase(Customer customer, LocalDate orderDate) {
		this.customer = customer;
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "P/O No. " + getId();
	}
}
