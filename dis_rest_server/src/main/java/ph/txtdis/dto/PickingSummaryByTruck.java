package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Item;
import ph.txtdis.domain.Truck;
import ph.txtdis.type.QualityType;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Subselect("select bd.id, p.pick_date, p.truck_id, bd.item_id, bd.quality_id, sum(bd.qty * qpu.qty) qty from picking p "
		+ "join picking_detail d on p.id = d.picking_id "
		+ "join booking b on d.booking_id = b.id join booking_detail bd on b.id = bd.booking_id "
		+ "join qty_per_uom qpu on bd.item_id = qpu.item_id and qpu.uom = bd.uom "
		+ "group by bd.id, p.pick_date, p.truck_id, bd.item_id, bd.quality_id ")
@Synchronize({ "picking", "picking_detail", "booking", "booking_detail", "item", "quality", "qty_per_uom" })
public class PickingSummaryByTruck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private LocalDate pickDate;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Truck truck;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Item item;

	@Column(nullable = false)
	private QualityType quality;

	@Column(nullable = false, precision = 10, scale = 4)
	private BigDecimal qty;

	public PickingSummaryByTruck(Item item, QualityType quality, BigDecimal qty) {
		this.item = item;
		this.quality = quality;
		this.qty = qty;
	}
}
