package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.BillingDetail;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BillableDetail extends AbstractId<Long> {

	private String itemName;

	private UomType uom;

	private BigDecimal qty;

	private QualityType quality;

	private BigDecimal priceValue;

	public BillableDetail(BillingDetail d) {
		setId(d.getItem().getId());
		itemName = d.getItem().getName();
		uom = d.getUom();
		qty = d.getQty();
		quality = d.getQuality();
		priceValue = d.getPriceValue();
	}
}
