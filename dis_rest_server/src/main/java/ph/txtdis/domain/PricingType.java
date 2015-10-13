package ph.txtdis.domain;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PricingType extends AbstractName {

	private static final long serialVersionUID = 8599562798765096281L;

	public PricingType(String name) {
		super(name);
	}
}
