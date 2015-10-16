package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolumeDiscount extends TrackedId {

	private static final long serialVersionUID = 7710563453808768120L;

	@Column(nullable = false)
	private VolumeDiscountType type;

	@Column(nullable = false)
	private UomType uom;

	@Column(nullable = false)
	private int cutOff;

	@Column(nullable = false, precision = 8, scale = 4)
	private BigDecimal discount;

	@Column(nullable = false)
	private LocalDate startDate;

	@ManyToOne
	private Channel channelLimit;

	public VolumeDiscount(VolumeDiscountType type, UomType uom, int cutOff, BigDecimal discount, LocalDate startDate) {
		this.type = type;
		this.uom = uom;
		this.cutOff = cutOff;
		this.discount = discount;
		this.startDate = startDate;
	}
}
