package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "booking_id", "item_id", "uom" }) )
public class BookingDetail extends SoldDetail {

	private static final long serialVersionUID = 7950095793861184523L;

	public BookingDetail(Item item, UomType uom, BigDecimal qty, QualityType quality) {
		super(item, uom, qty, quality);
	}
}
