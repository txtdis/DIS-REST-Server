package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Discount extends AbstractId implements Percentage {

	private static final long serialVersionUID = 5694320736773645719L;

	@Column(nullable = false)
	private int level;

	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal percent;
}
