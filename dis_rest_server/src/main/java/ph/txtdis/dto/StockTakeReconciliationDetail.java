package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Item;
import ph.txtdis.type.QualityType;

@Data
@NoArgsConstructor
public class StockTakeReconciliationDetail {

    private Item item;

    private QualityType quality;

    private BigDecimal startQty = BigDecimal.ZERO;

    private BigDecimal startAdjustQty = BigDecimal.ZERO;

    private BigDecimal inQty = BigDecimal.ZERO;

    private BigDecimal outQty = BigDecimal.ZERO;

    private BigDecimal countQty = BigDecimal.ZERO;

    private BigDecimal adjustmentQty = BigDecimal.ZERO;

    private String justification;

    public BigDecimal getSystemQty() {
        return getStartQty().add(getStartAdjustQty()).add(getInQty()).subtract(
                getOutQty());
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
