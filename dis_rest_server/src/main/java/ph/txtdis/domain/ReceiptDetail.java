package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiptDetail extends SoldDetail {

    private static final long serialVersionUID = -6383895333116700458L;

    public ReceiptDetail(Item item, UomType uom, BigDecimal qty,
            QualityType quality) {
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }
}
