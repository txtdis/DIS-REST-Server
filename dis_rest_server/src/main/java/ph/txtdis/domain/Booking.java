package ph.txtdis.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = { @Index(columnList = "orderDate") })
public class Booking extends AbstractPrinted {

	private static final long serialVersionUID = 7359559924256955582L;

	@JoinColumn(name = "booking_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<BookingDetail> details;

	@JoinColumn(name = "booking_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<BookingDiscount> discounts;

	public Booking(Customer customer, LocalDate orderDate) {
		setCustomer(customer);
		setOrderDate(orderDate);
	}

	@Override
	public String toString() {
		return "S/O No. " + id;
	}
}
