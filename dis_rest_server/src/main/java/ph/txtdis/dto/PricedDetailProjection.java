package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import ph.txtdis.domain.SoldDetail;

@Getter
@Setter
public abstract class PricedDetailProjection extends OrderDetailProjection {

	protected BigDecimal priceValue;

	protected BigDecimal subtotalValue;

	public PricedDetailProjection(SoldDetail e) {
		super(e);
		priceValue = e.getPriceValue();
		subtotalValue = priceValue.multiply(e.getQty());
	}
}
