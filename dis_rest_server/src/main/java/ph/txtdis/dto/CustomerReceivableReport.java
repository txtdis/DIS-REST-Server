package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReceivableReport {

	private List<CustomerReceivable> receivables;

	private List<BigDecimal> totals;

	private String customer;

	private ZonedDateTime timestamp;
}
