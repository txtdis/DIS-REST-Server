package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Invoice;

@Data
@NoArgsConstructor
public class AgingReceivable {

	private Long id;

	private String seller, customer;

	private BigDecimal currentValue, oneToSevenValue, eightToFifteenValue, sixteenToThirtyValue, greaterThanThirtyValue,
			agingValue, totalValue;

	public AgingReceivable(Invoice i) {
		id = i.getCustomerId();
		customer = i.getCustomerName();
		seller = i.getSeller();
		currentValue = BigDecimal.ZERO;
		oneToSevenValue = BigDecimal.ZERO;
		eightToFifteenValue = BigDecimal.ZERO;
		sixteenToThirtyValue = BigDecimal.ZERO;
		greaterThanThirtyValue = BigDecimal.ZERO;
		agingValue = BigDecimal.ZERO;
		totalValue = BigDecimal.ZERO;
	}
}