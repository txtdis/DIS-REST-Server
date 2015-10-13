package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends AbstractId {

	private static final long serialVersionUID = 4346280650221114961L;

	@Column(nullable = false)
	private LocalDate paymentDate;

	@Column(nullable = false)
	private BigDecimal paymentValue;

	public Payment(LocalDate paymentDate, BigDecimal paymentValue) {
		this.paymentDate = paymentDate;
		this.paymentValue = paymentValue;
	}
}
