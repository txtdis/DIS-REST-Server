package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import static ph.txtdis.util.Numeric.toPercentRate;

import static java.math.BigDecimal.ZERO;

import static lombok.AccessLevel.PROTECTED;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public abstract class SoldOrder extends TrackedOrder implements Detailed, Discounted {

	private static final long serialVersionUID = 3715783817464199036L;

	@ManyToOne(optional = false)
	private Customer customer;

	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;

	private String remarks;

	public CreditDetail getCredit() {
		return getCustomer() == null ? null : getCustomer().getCredit(getOrderDate());
	}

	public Long getCustomerId() {
		return getCustomer() == null ? null : getCustomer().getId();
	}

	public String getCustomerName() {
		return getCustomer() == null ? null : getCustomer().getName();
	}

	public BigDecimal getDiscountValue() {
		return getDiscounts() == null ? ZERO : computeDiscount(getGrossValue());
	}

	public BigDecimal getGrossValue() {
		return getDetails() == null ? null
				: getDetails().stream().map(d -> d.getSubtotalValue()).reduce(ZERO, (a, b) -> a.add(b));
	}

	public Route getRoute() {
		return getCustomer() == null ? null : getCustomer().getRoute(getOrderDate());
	}

	public String getSeller() {
		Route r = getRoute();
		return r == null ? null : r.getSeller(getOrderDate());
	}

	public BigDecimal getValue() {
		return getGrossValue() == null ? ZERO : getGrossValue().subtract(getDiscountValue());
	}

	private BigDecimal computeDiscount(BigDecimal value) {
		BigDecimal discount = ZERO;
		for (Discount d : getDiscounts()) {
			discount = discount.add(value.multiply(toPercentRate(d.getPercent())));
			value = value.subtract(discount);
		}
		return discount;
	}
}
