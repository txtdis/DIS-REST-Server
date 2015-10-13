package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseDetail extends SoldDetail {

    private static final long serialVersionUID = 4692138441515885681L;

    private String justification;

    public PurchaseDetail(Item item, UomType uom, BigDecimal qty,
            BigDecimal price, QualityType quality,
            String justification) {
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.priceValue = price;
        this.quality = quality;
        this.justification = justification;
    }
}
