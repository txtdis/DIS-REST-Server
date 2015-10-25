package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "remittance_detail")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceDetail extends TrackedOrder {

	private static final long serialVersionUID = -968954060788678059L;

	@ManyToOne
	private Remittance remittance;

	@ManyToOne(cascade = CascadeType.ALL)
	private Invoice invoice;

	@Column(name = "payment", nullable = false, precision = 8, scale = 2)
	private BigDecimal paymentValue;
}
