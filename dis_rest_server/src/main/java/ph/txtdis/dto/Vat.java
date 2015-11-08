package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Billing;

@Getter
@NoArgsConstructor
public class Vat {

	private Long id;

	private String prefix;

	private Long nbrId;

	private String suffix;

	private String customer;

	private LocalDate orderDate;

	private BigDecimal value;

	private BigDecimal vatValue;

	public Vat(Billing i) {
		id = i.getId();
		prefix = i.getPrefix();
		nbrId = i.getNumId();
		suffix = i.getSuffix();
		customer = i.getCustomer().getName();
		orderDate = i.getOrderDate();
		value = i.getTotalValue();
	}

	public void setValue(BigDecimal v) {
		value = v;
	}

	public void setVat(BigDecimal v) {
		vatValue = v;
	}
}
