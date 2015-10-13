package ph.txtdis.domain;

import java.util.List;

public interface Detailed<T extends Subtotalled> {

	List<T> getDetails();
}
