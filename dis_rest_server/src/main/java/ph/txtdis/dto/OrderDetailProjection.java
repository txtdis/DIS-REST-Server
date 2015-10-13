package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import ph.txtdis.domain.OrderDetail;

@Getter
@Setter
public abstract class OrderDetailProjection {

	protected Long itemId;

	protected String itemName;

	protected String uom;

	protected BigDecimal qty;

	protected String quality;

	public OrderDetailProjection(OrderDetail e) {
		this.itemId = e.getItem().getId();
		this.itemName = e.getItem().getName();
		this.uom = e.getUom().toString();
		this.qty = e.getQty();
		this.quality = e.getQuality().toString();
	}
}
