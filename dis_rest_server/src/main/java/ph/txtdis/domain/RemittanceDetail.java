package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "remittance_detail")
@EqualsAndHashCode(callSuper = true)
public class RemittanceDetail extends AbstractId {

	private static final long serialVersionUID = -968954060788678059L;

	@ManyToOne(optional = false)
	private Remittance remittance;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Billing billing;

	@Column(name = "payment", nullable = false, precision = 8, scale = 2)
	private BigDecimal paymentValue;
}
