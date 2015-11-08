package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(
// @formatter:off
	indexes = {
		@Index(columnList = "is_fully_paid, customer_id, unpaid, order_date"),
		@Index(columnList = "is_fully_paid, unpaid, order_date"),
		@Index(columnList = "prefix, suffix, id_no"),
		@Index(columnList = "order_date"),
		@Index(columnList = "id_no, created_on"),
		@Index(columnList = "id_no, id"),
		@Index(columnList = "booking_id, created_on") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "prefix", "id_no", "suffix" }) )
//@formatter:on
public class Billing extends SoldOrder {

	private static final long serialVersionUID = -4363805360652350591L;

	private String prefix, suffix;

	@Column(name = "id_no")
	private Long numId;

	@Column(name = "booking_id")
	private Long bookingId;

	@Column(name = "receiving_id")
	private Long receivingId;

	@Column(name = "purchasing_id")
	private Long purchasingId;

	@OneToMany
	@JoinColumn(name = "billing_id")
	private List<BillingDetail> details;

	@OneToMany(mappedBy = "billing", cascade = CascadeType.ALL)
	private List<RemittanceDetail> payments;

	@ManyToMany
	private List<Discount> discounts;

	@Column(name = "is_fully_paid")
	private Boolean fullyPaid;

	@Column(name = "is_printed")
	private Boolean printed;

	@Column(name = "actual", precision = 8, scale = 2)
	private BigDecimal actualValue;

	@Column(name = "unpaid", precision = 8, scale = 2)
	private BigDecimal unpaidValue;

	@Column(name = "billed_by")
	private String billedBy;

	@Column(name = "billed_on")
	private ZonedDateTime billedOn;

	@Column(name = "received_by")
	private String receivedBy;

	@Column(name = "received_on")
	private ZonedDateTime receivedOn;

	public String getOrderNo() {
		return prefix() + numId + suffix();
	}

	private String prefix() {
		return prefix == null ? "" : prefix + "-";
	}

	private String suffix() {
		return suffix == null ? "" : suffix;
	}
}
