package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import ph.txtdis.domain.Keyed;

@Data
public class Inventory implements Keyed<Long> {

	private Long id;

	private String item;

	private Integer daysLevel;

	private BigDecimal avgDailySoldQty, goodQty, badQty, value, obsolesenceValue;
}
