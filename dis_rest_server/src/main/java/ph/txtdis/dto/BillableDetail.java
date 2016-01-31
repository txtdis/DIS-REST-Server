package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@EqualsAndHashCode(callSuper = true)
public class BillableDetail extends AbstractId<Long> {

	private String itemName;

	private UomType uom;

	private BigDecimal initialQty, returnedQty;

	private QualityType quality;

	private BigDecimal priceValue;

	private Integer onPurchaseDaysLevel, onReceiptDaysLevel;
}
