package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
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
		@Index(columnList = "customer_id, fullyPaid"),
		@Index(columnList = "idPrefix, idNo, idSuffix"),
		@Index(columnList = "booking_id"),
		@Index(columnList = "fullyPaid"),
		@Index(columnList = "orderDate") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "idPrefix", "idNo", "idSuffix" }) )
//@formatter:on
public class Invoice extends SoldOrder<InvoiceDetail, InvoiceDiscount> {

	private static final long serialVersionUID = -4363805360652350591L;

	private String idPrefix, idSuffix;

	private Long idNo;

	@OneToOne
	private Booking booking;

	@JoinColumn(name = "invoice_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<InvoiceDetail> details;

	@JoinColumn(name = "invoice_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<InvoiceDiscount> discounts;

	@JsonIgnore
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<RemittanceDetail> remittances;

	@JsonIgnore
	private boolean fullyPaid;

	public List<RemittancePayment> getPayments() {
		return getRemittances() == null ? null
				: getRemittances().stream()
						.filter(r -> r.getRemittance().getRemitDate().compareTo(LocalDate.now()) <= 0)
						.map(r -> new RemittancePayment(r)).collect(Collectors.toList());
	}

	public BigDecimal getUnpaidValue() {
		return getValue().subtract(getRemittances() == null ? BigDecimal.ZERO : sumPayments());
	}

	private BigDecimal sumPayments() {
		return remittances.stream().map(r -> r.getPaymentValue()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
}
