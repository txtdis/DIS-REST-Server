package ph.txtdis.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Properties;

import lombok.Getter;
import ph.txtdis.domain.Invoice;
import ph.txtdis.util.Numeric;

@Getter
public class Vat {

	private Long id;

	private String prefix;

	private Long nbrId;

	private String suffix;

	private String customer;

	private LocalDate orderDate;

	private BigDecimal value;

	private BigDecimal vatValue;

	public Vat() {
		value = new BigDecimal("1000.00");
		vatValue = vatValue(value);
	}

	public Vat(Invoice i) {
		id = i.getId();
		prefix = i.getPrefix();
		nbrId = i.getNbrId();
		suffix = i.getSuffix();
		customer = i.getCustomerName();
		orderDate = i.getOrderDate();
		value = i.getValue();
		vatValue = vatValue(value);
	}

	private Properties loadProperties() throws IOException {
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/config/application.properties"));
		return props;
	}

	private Properties properties() {
		try {
			return loadProperties();
		} catch (IOException e) {
			return null;
		}
	}

	private BigDecimal vatRate() {
		String vatText = properties().getProperty("vat.percent");
		BigDecimal vatPerCent = new BigDecimal(vatText);
		return Numeric.toPercentRate(vatPerCent).add(BigDecimal.ONE);
	}

	private BigDecimal vatValue(BigDecimal value) {
		return value == null ? null : value.subtract(Numeric.divide(value, vatRate()));
	}
}
