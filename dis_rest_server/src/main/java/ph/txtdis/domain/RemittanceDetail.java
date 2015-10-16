package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceDetail extends TrackedId {

	private static final long serialVersionUID = -968954060788678059L;

	@ManyToOne
	private Remittance remittance;

	@ManyToOne(cascade = CascadeType.ALL)
	private Invoice invoice;

	@Column(nullable = false)
	private BigDecimal paymentValue;
}
