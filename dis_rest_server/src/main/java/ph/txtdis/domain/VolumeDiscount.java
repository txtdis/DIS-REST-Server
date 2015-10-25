package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "volume_discount")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolumeDiscount extends TrackedOrder {

	private static final long serialVersionUID = 7710563453808768120L;

	@Column(nullable = false)
	private VolumeDiscountType type;

	@Column(nullable = false)
	private UomType uom;

	private int cutoff;

	@Column(nullable = false, precision = 8, scale = 4)
	private BigDecimal discount;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@ManyToOne
	@JoinColumn(name = "channel_limit")
	private Channel channelLimit;

	public VolumeDiscount(VolumeDiscountType type, UomType uom, int cutoff, BigDecimal discount, LocalDate startDate) {
		this.type = type;
		this.uom = uom;
		this.cutoff = cutoff;
		this.discount = discount;
		this.startDate = startDate;
	}
}
