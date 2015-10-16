package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "item_id", "startDate", "type_id", "channelLimit_id" }) )
public class Price extends TrackedId implements Comparable<Price> {

	private static final long serialVersionUID = -5721868302278165093L;

	@ManyToOne(optional = false)
	private PricingType type;

	@Column(nullable = false)
	private BigDecimal priceValue;

	@Column(nullable = false)
	private LocalDate startDate;

	@ManyToOne
	private Channel channelLimit;

	public Price(PricingType type, BigDecimal priceValue, LocalDate startDate) {
		this.type = type;
		this.priceValue = priceValue;
		this.startDate = startDate;
	}

	@Override
	public int compareTo(Price price) {
		return getStartDate().compareTo(price.getStartDate());
	}
}
