package ph.txtdis.dto;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static ph.txtdis.util.NumberUtils.isZero;

import java.math.BigDecimal;
import java.time.LocalDate;

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
		daysOverCount = dueDate.until(now(), DAYS);
		unpaidValue = unpaid(i);
		totalValue = i.getTotalValue();
	}

	private boolean isPostDatedCheck(Billing i) {
		try {
			return !i.getFullyPaid() && isZero(i.getUnpaidValue());
		} catch (Exception e) {
			return false;
		}
	}

	private BigDecimal unpaid(Billing i) {
		return isPostDatedCheck(i) ? i.getTotalValue() : i.getUnpaidValue();
	}
}