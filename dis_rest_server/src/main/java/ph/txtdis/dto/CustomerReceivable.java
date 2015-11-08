package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Billing;

@Data
@NoArgsConstructor
public class CustomerReceivable {

	private Long id;

	private String orderNo;

	private LocalDate orderDate;

	private LocalDate dueDate;

	private long daysOverCount;

	private BigDecimal unpaidValue;

	private BigDecimal totalValue;

	public CustomerReceivable(Billing i) {
		id = i.getId();
		orderNo = i.getOrderNo();
		orderDate = i.getOrderDate();
		dueDate = i.getDueDate();
		daysOverCount = dueDate.until(LocalDate.now(), ChronoUnit.DAYS);
		unpaidValue = i.getUnpaidValue();
		totalValue = i.getTotalValue();
	}
}