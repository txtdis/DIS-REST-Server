package ph.txtdis.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Invoice;
import ph.txtdis.util.Numeric;

@Getter
@NoArgsConstructor
public class Vat {

	private Long id;

	private String customer;

	private LocalDate orderDate;

	private BigDecimal value;

	private BigDecimal vatValue;

	public Vat(Invoice i) {
		this(i.getId(), i.getCustomer().getName(), i.getOrderDate(), i.getValue());
	}

	public Vat(Long id, String customer, LocalDate orderDate, BigDecimal value) {
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
		this.value = value;
		this.vatValue = vatValue(value);
	}

	private BigDecimal vatValue(BigDecimal value) {
		return value == null ? null : value.subtract(Numeric.divide(value, vatRate()));
	}

	private BigDecimal vatRate() {
		String vatText = properties().getProperty("vat.percent");
		BigDecimal vatPerCent = new BigDecimal(vatText);
		return Numeric.toPercentRate(vatPerCent).add(BigDecimal.ONE);
	}

	private Properties properties() {
		try {
			return loadProperties();
		} catch (IOException e) {
			return null;
		}
	}

	private Properties loadProperties() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/config/application.properties"));
		return props;
	}
}
