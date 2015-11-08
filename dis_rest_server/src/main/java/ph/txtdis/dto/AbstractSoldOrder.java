package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ph.txtdis.domain.Discount;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractSoldOrder<PK> extends AbstractAuditedId<PK> {

	private BigDecimal value;

	private String customerName;

	private List<BillableDetail> details;

	private List<Discount> discounts;

	private LocalDate dueDate, orderDate;

	private String remarks;
}
