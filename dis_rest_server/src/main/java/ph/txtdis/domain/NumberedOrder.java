package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NumberedOrder extends AuditedId {

	private static final long serialVersionUID = 2042906262421586568L;

	@ManyToOne(optional = false)
	protected Customer customer;

	@Column(nullable = false)
	protected LocalDate orderDate;

	private String remarks;
}
