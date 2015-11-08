package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.math.BigDecimal.ZERO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Data
@Entity
@Table(name = "billing_detail")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillingDetail extends AbstractId {

	private static final long serialVersionUID = 257754573072417395L;

	@ManyToOne(optional = false)
	private Item item;

	@Column(nullable = false)
	private UomType uom;

	@JsonIgnore
	@Column(name = "initial_qty", nullable = false, precision = 10, scale = 4)
	private BigDecimal initialQty;

	@JsonIgnore
	@Column(name = "returned_qty", precision = 10, scale = 4)
	private BigDecimal returnedQty;

	@Column(nullable = false)
	private QualityType quality;

	@Column(name = "price", nullable = false, precision = 8, scale = 2)
	private BigDecimal priceValue;

	public BillingDetail(Item item, UomType uom, BigDecimal qty, QualityType quality) {
		this.item = item;
		this.uom = uom;
		this.initialQty = qty;
		this.quality = quality;
	}

	public BigDecimal getInitialQty() {
		return initialQty == null ? ZERO : initialQty;
	}

	public BigDecimal getQty() {
		return getInitialQty().subtract(getReturnedQty());
	}

	public BigDecimal getReturnedQty() {
		return returnedQty == null ? ZERO : returnedQty;
	}
}
