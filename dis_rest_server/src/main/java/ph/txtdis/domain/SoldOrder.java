package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.util.Numeric;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SoldOrder<TD extends Subtotalled, D extends Percentage> extends AuditedId
		implements Detailed<TD>, Discounted<D>
{

	private static final long serialVersionUID = 3715783817464199036L;

	@ManyToOne(optional = false)
	private Customer customer;

	@ManyToOne
	private CreditDetail credit;

	@Column(nullable = false)
	private LocalDate orderDate;

	private String remarks;

	public Long getCustomerId() {
		return getCustomer() == null ? null : getCustomer().getId();
	}

	public String getCustomerName() {
		return getCustomer() == null ? null : getCustomer().getName();
	}

	public Route getRoute() {
		return getCustomer() == null ? null : getCustomer().getRoute(getOrderDate());
	}

	public String getSeller() {
		Route r = getRoute();
		return r == null ? null : r.getSeller(getOrderDate());
	}

	public BigDecimal getValue() {
		return getGrossValue() == null ? BigDecimal.ZERO : getGrossValue().subtract(getDiscountValue());
	}

	private BigDecimal getGrossValue() {
		return getDetails() == null ? null
				: getDetails().stream().map(d -> d.getSubtotalValue()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}

	private BigDecimal getDiscountValue() {
		return getDiscounts() == null ? BigDecimal.ZERO : computeDiscount(getGrossValue());
	}

	private BigDecimal computeDiscount(BigDecimal value) {
		BigDecimal discount = BigDecimal.ZERO;
		for (D d : getDiscounts()) {
			discount = discount.add(value.multiply(Numeric.toPercentRate(d.getPercent())));
			value = value.subtract(discount);
		}
		return discount;
	}
}
