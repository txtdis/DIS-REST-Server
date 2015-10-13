package ph.txtdis.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingDiscount extends Discount {

	private static final long serialVersionUID = -6874511486259178698L;

	public BookingDiscount(int level, BigDecimal perCent) {
		super(level, perCent);
	}
}
