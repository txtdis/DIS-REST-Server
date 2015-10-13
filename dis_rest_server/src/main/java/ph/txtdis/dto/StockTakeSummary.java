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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Item;
import ph.txtdis.type.QualityType;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Subselect("select d.id, s.stock_take_date, d.item_id, d.quality_id, sum(d.qty * qpu.qty) qty from stock_take s "
		+ "join stock_take_detail d on s.id = d.stock_take_id "
		+ "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
		+ "group by d.id, s.stock_take_date, d.item_id, d.quality_id ")
@Synchronize({ "stock_take", "stock_take_detail", "item", "quality", "qty_per_uom" })
public class StockTakeSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private LocalDate stockTakeDate;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Item item;

	@Column(nullable = false)
	private QualityType quality;

	@Column(nullable = false, precision = 10, scale = 4)
	private BigDecimal qty;

	public StockTakeSummary(Item item, QualityType quality, BigDecimal qty) {
		this.item = item;
		this.quality = quality;
		this.qty = qty;
	}
}
