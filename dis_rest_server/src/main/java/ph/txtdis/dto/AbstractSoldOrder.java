package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ph.txtdis.domain.CustomerDiscount;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractSoldOrder<PK> extends EntityDecisionNeeded<PK> {

	private BigDecimal value;

	private String customerName;

	private List<BillableDetail> details;

	private List<CustomerDiscount> customerDiscounts;

	private LocalDate dueDate, orderDate;

	private String remarks;
}
