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
public class AgingReceivableReport {

	private List<AgingReceivable> receivables;

	private List<BigDecimal> totals;

	private ZonedDateTime timestamp;
}
