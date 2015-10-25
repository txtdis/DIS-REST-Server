package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.printer.Printed;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = { @Index(columnList = "order_date") })
public class Booking extends SoldOrder implements Printed {

	private static final long serialVersionUID = 7359559924256955582L;

	@JoinColumn(name = "booking_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<SoldDetail> details;

	@ManyToMany
	@JoinTable(name = "booking_discount", joinColumns = @JoinColumn(name = "booking_id") ,
			inverseJoinColumns = @JoinColumn(name = "discount_id") )
	private List<Discount> discounts;

	@Column(name = "printed_by")
	private String printedBy;

	@Column(name = "printed_on")
	private ZonedDateTime printedOn;

	public Booking(Customer customer, LocalDate orderDate) {
		setCustomer(customer);
		setOrderDate(orderDate);
	}

	@Override
	public String toString() {
		return "S/O No. " + id;
	}
}
