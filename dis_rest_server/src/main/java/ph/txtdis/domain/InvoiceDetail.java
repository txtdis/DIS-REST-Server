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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "invoice_id", "item_id", "uom" }) )
public class InvoiceDetail extends SoldDetail {

	private static final long serialVersionUID = 5296998573316461332L;

	public InvoiceDetail(Item item, UomType uom, BigDecimal qty, QualityType quality, BigDecimal price) {
		super(item, uom, qty, quality);
		setPriceValue(price);
	}
}
