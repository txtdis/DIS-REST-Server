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
import ph.txtdis.type.UomType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bom extends TrackedOrder {

    private static final long serialVersionUID = -86213987350272414L;

    @ManyToOne(optional = false)
    private Item part;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal qty;
}
