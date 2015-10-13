package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SoldDetail extends OrderDetail implements Subtotalled {

	private static final long serialVersionUID = 257754573072417395L;

	@Column(nullable = false)
	protected BigDecimal priceValue;

	public SoldDetail(Item item, UomType uom, BigDecimal qty, QualityType quality) {
		super(item, uom, qty, quality);
	}

	@Override
	public BigDecimal getSubtotalValue() {
		return getQty().multiply(getPriceValue());
	}
}
