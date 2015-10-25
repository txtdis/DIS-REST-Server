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
@Table(name = "credit_detail", uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "start_date" }) )
public class CreditDetail extends TrackedOrder {

	private static final long serialVersionUID = 1655742390812653142L;

	@Column(name = "term")
	private Integer termInDays;

	@Column(name = "grace_period")
	private Integer gracePeriodInDays;

	@Column(name = "credit_limit", precision = 9, scale = 2)
	private BigDecimal creditLimit;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
}
