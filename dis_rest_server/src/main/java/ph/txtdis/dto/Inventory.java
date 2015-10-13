package ph.txtdis.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Item;
import ph.txtdis.type.QualityType;
import ph.txtdis.util.Numeric;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    private Item item;

    private QualityType quality;

    private BigDecimal startQty = BigDecimal.ZERO;

    private BigDecimal startAdjustQty = BigDecimal.ZERO;

    private BigDecimal inQty = BigDecimal.ZERO;

    private BigDecimal outQty = BigDecimal.ZERO;

    private BigDecimal avg4wkSoldQty = new BigDecimal("0.0001");

    public long getItemId() {
        return item == null ? 0 : item.getId();
    }

    public BigDecimal getBeginQty() {
        return getStartQty().add(getStartAdjustQty());
    }

    public BigDecimal getEndQty() {
        return getBeginQty().add(getInQty()).subtract(getOutQty());
    }

    public String getDaysLevel() {
        int daysLevel = getEndQty().divide(getAvg4wkSoldQty(), 0,
                RoundingMode.HALF_EVEN).intValue();
        return daysLevel > 365 ? ">365" : Numeric.formatInt(daysLevel);
    }
}
