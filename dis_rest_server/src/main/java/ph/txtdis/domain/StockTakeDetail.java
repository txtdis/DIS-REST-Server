package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class StockTakeDetail extends TrackedId {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false)
    private Item item;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    @Column(nullable = false)
    private QualityType quality;
}
