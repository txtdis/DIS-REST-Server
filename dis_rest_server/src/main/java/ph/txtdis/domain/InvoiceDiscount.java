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
public class InvoiceDiscount extends Discount {

	private static final long serialVersionUID = 2988714572604940140L;

	public InvoiceDiscount(int level, BigDecimal percent) {
		super(level, percent);
	}
}
