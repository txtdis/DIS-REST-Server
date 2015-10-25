package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ph.txtdis.dto.RemittancePayment;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(
// @formatter:off
	indexes = {
		@Index(columnList = "customer_id, is_fully_paid"),
		@Index(columnList = "prefix, id_no, suffix"),
		@Index(columnList = "booking_id"),
		@Index(columnList = "is_fully_paid"),
		@Index(columnList = "order_date") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "prefix", "id_no", "suffix" }) )
//@formatter:on
public class Invoice extends SoldOrder {

	private static final long serialVersionUID = -4363805360652350591L;

	private String prefix, suffix;

	@Column(name = "id_no")
	private Long numId;

	@OneToOne
	private Booking booking;

	@OneToMany
	@JoinColumn(name = "invoice_id")
	private List<SoldDetail> details;

	@ManyToMany
	@JoinTable(name = "invoice_discount", joinColumns = @JoinColumn(name = "invoice_id") ,
			inverseJoinColumns = @JoinColumn(name = "discount_id") )
	private List<Discount> discounts;

	@JsonIgnore
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<RemittanceDetail> remittances;

	@JsonIgnore
	@Column(name = "is_fully_paid")
	private boolean fullyPaid;

	@Column(name = "actual", nullable = false, precision = 8, scale = 2)
	private BigDecimal actualValue;

	public Long getNumId() {
		return numId == null ? -id : numId;
	}

	public String getOrderNo() {
		return prefix() + numId + suffix();
	}

	public List<RemittancePayment> getPayments() {
		return getRemittances() == null ? null
				: getRemittances().stream().filter(r -> r.getRemittance().getDueDate().compareTo(LocalDate.now()) <= 0)
						.map(r -> new RemittancePayment(r)).collect(Collectors.toList());
	}

	public BigDecimal getUnpaidValue() {
		return getValue().subtract(getRemittances() == null ? BigDecimal.ZERO : sumPayments());
	}

	private String prefix() {
		return prefix == null ? "" : prefix + "-";
	}

	private String suffix() {
		return suffix == null ? "" : suffix;
	}

	private BigDecimal sumPayments() {
		return remittances.stream().map(r -> r.getPaymentValue()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
}
