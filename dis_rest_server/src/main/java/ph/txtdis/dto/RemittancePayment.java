package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import ph.txtdis.domain.RemittanceDetail;

@Data
public class RemittancePayment {

	private Long remitId;

	private LocalDate remitDate;

	private BigDecimal value;

	public RemittancePayment(RemittanceDetail d) {
		remitId = d.getRemittance().getId();
		remitDate = d.getRemittance().getDueDate();
		value = d.getPaymentValue();
	}
}
