package ph.txtdis.domain;

import java.util.List;

public interface Discounted<T extends Percentage> {

	List<T> getDiscounts();
}
