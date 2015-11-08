package ph.txtdis.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Route extends AbstractName {

	private static final long serialVersionUID = -593813397375404049L;

	@JoinColumn(name = "route_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> sellerHistory;

	public Route(String name) {
		super(name);
	}

	public String getSeller(LocalDate date) {
		try {
			return getSellerHistory().stream().filter(p -> p.getStartDate().compareTo(date) <= 0)
					.max((a, b) -> a.getStartDate().compareTo(b.getStartDate())).get().getSeller();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
