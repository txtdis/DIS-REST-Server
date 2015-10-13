package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@MappedSuperclass
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class OrderDetail extends AuditedId {

	private static final long serialVersionUID = 1774123388619693560L;

	@ManyToOne(optional = false)
	protected Item item;

	@Column(nullable = false)
	protected UomType uom;

	@Column(nullable = false, precision = 10, scale = 4)
	protected BigDecimal qty;

	@Column(nullable = false)
	protected QualityType quality;
}
