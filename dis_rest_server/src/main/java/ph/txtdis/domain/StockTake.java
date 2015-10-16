package ph.txtdis.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTake extends TrackedId {

	private static final long serialVersionUID = 7630222117410458884L;

	@ManyToOne(optional = false)
	private Warehouse warehouse;

	@ManyToOne(optional = false)
	private User taker;

	@ManyToOne(optional = false)
	private User checker;

	@Column(nullable = false)
	private LocalDate stockTakeDate;

	@JoinColumn(name = "stock_take_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<StockTakeDetail> details;

	public StockTake(Warehouse warehouse, User taker, User checker, LocalDate stockTakeDate) {
		this.warehouse = warehouse;
		this.taker = taker;
		this.checker = checker;
		this.stockTakeDate = stockTakeDate;
	}
}
