package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import static lombok.AccessLevel.PROTECTED;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public abstract class SoldOrder extends AuditedOrder implements Detailed, Discounted {

	private static final long serialVersionUID = 3715783817464199036L;

	@ManyToOne(optional = false)
	private Customer customer;

	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate;

	@Column(name = "gross", precision = 8, scale = 2)
	private BigDecimal grossValue;

	@Column(name = "total", precision = 8, scale = 2)
	private BigDecimal totalValue;

	private String remarks;
}
