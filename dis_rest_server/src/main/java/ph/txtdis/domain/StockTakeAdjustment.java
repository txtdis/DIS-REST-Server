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
import ph.txtdis.type.QualityType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTakeAdjustment extends AuditedId {

	private static final long serialVersionUID = -1676592025325317933L;

	@Column(nullable = false)
	private LocalDate stockTakeDate;

	@ManyToOne(optional = false)
	private Item item;

	@Column(nullable = false)
	private QualityType quality;

	@Column(precision = 10, scale = 4)
	private BigDecimal qty;

	@Column(nullable = false)
	private String justification;
}
