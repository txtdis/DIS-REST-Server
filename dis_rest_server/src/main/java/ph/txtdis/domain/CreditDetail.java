package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "startDate" }) )
public class CreditDetail extends TrackedId {

	private static final long serialVersionUID = 1655742390812653142L;

	private int termInDays, gracePeriodInDays;

	@Column(nullable = false, precision = 9, scale = 2)
	private BigDecimal creditLimit;

	@Column(nullable = false)
	private LocalDate startDate;
}
