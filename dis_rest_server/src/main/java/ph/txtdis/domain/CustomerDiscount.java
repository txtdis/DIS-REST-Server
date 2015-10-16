package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
//@formatter:off
@Table(
	indexes = { @Index(columnList = "customer_id, startDate") },
	uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "startDate", "level", "familyLimit_id" }) )
//@formatter:on
public class CustomerDiscount extends TrackedId {

	private static final long serialVersionUID = -455882680349394952L;

	@Column(nullable = false)
	private int level;

	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal percent;

	@ManyToOne
	private ItemFamily familyLimit;

	@Column(nullable = false)
	private LocalDate startDate;
}
