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

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("select rd.id, r.order_date, p.truck_id, rd.item_id, rd.quality_id, sum(rd.qty * qpu.qty) qty from picking p "
		+ "join picking_detail pd on p.id = pd.picking_id "
		+ "join booking b on pd.booking_id = b.id join booking_detail bd on b.id = bd.booking_id "
		+ "join receiving r on r.order_date = p.pick_date and r.partner_id = b.partner_id "
		+ "join receiving_detail rd on r.id = rd.receiving_id and rd.item_id = bd.item_id "
		+ "join qty_per_uom qpu on rd.item_id = qpu.item_id and qpu.uom = rd.uom "
		+ "group by rd.id, r.order_date, p.truck_id, rd.item_id, rd.quality_id ")
@Synchronize({ "receiving", "receiving_detail", "picking", "picking_detail", "booking", "booking_detail", "item",
		"quality", "qty_per_uom" })
public class ReceivingSummaryByTruck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private LocalDate orderDate;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Truck truck;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Item item;

	private QualityType quality;

	@Column(nullable = false, precision = 10, scale = 4)
	private BigDecimal qty;

	public ReceivingSummaryByTruck(Item item, QualityType quality, BigDecimal qty) {
		this.item = item;
		this.quality = quality;
		this.qty = qty;
	}
}
