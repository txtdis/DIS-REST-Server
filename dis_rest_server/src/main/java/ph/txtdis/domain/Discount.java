package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
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
@Table(
//@formatter:off
	indexes = { @Index(columnList = "customer_id, start_date") },
	uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "start_date", "level", "family_limit_id" }) )
//@formatter:on
public class Discount extends TrackedOrder {

	private static final long serialVersionUID = -455882680349394952L;

	private int level;

	@Column(precision = 5, scale = 2)
	private BigDecimal percent;

	@ManyToOne
	@JoinColumn(name = "family_limit_id")
	private ItemFamily familyLimit;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
}
