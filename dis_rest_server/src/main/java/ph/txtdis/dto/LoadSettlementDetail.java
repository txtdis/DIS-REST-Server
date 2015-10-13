package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Item;
import ph.txtdis.util.DateTime;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadSettlementDetail {

    private Item item;

    private BigDecimal pickedQty = BigDecimal.ZERO;

    private BigDecimal soldQty = BigDecimal.ZERO;

    private BigDecimal returnedQty = BigDecimal.ZERO;

    private String actionTaken;

    public LoadSettlementDetail(Item item, Double pickedQty, Double soldQty, Double returnedQty, String actionTaken) {
        this.item = item;
        this.pickedQty = DateTime.toBigDecimal(pickedQty);
        this.soldQty = DateTime.toBigDecimal(soldQty);
        this.returnedQty = DateTime.toBigDecimal(returnedQty);
        this.actionTaken = actionTaken;
    }

    public long getItemId() {
        return item == null ? 0 : item.getId();
    }

    public BigDecimal getVarianceQty() {
        return getSoldQty().add(getReturnedQty()).subtract(getPickedQty());
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
