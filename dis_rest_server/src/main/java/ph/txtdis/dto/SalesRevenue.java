package ph.txtdis.dto;

import java.math.BigDecimal;

import lombok.Data;
import ph.txtdis.domain.Keyed;

@Data
public class SalesRevenue implements Keyed<Long>, Comparable<SalesRevenue> {

	private Long id;

	private String seller, customer;

	private BigDecimal value;

	@Override
	public int compareTo(SalesRevenue v) {
		return getId().compareTo(v.getId());
	}
}
