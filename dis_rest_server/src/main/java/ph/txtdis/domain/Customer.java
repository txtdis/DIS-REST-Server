package ph.txtdis.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(indexes = { @Index(columnList = "name") })
public class Customer extends AbstractDeactivatedName {

	private static final long serialVersionUID = -878749889584633340L;

	private String street;

	@ManyToOne(optional = false)
	private Location barangay;

	@ManyToOne(optional = false)
	private Location city;

	@ManyToOne(optional = false)
	private Location province;

	@ManyToOne(optional = false)
	private PricingType primaryPricingType;

	@ManyToOne
	private PricingType alternatePricingType;

	private CustomerType type;

	@ManyToOne
	private Channel channel;

	private VisitFrequency visitFrequency;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Routing> routeHistory;

	private String contactName, contactSurname, contactTitle;

	private String mobile;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<CreditDetail> creditDetails;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<CustomerDiscount> discounts;

	@ManyToOne
	private Customer parent;

	public Customer(String name, CustomerType type) {
		super(name);
		this.type = type;
	}

	public Route getRoute(LocalDate date) {
		return getRouteHistory() == null ? null
				: getRouteHistory().stream().filter(p -> p.getStartDate().compareTo(date) <= 0).max(Routing::compareTo)
						.get().getRoute();
	}

	public String getSeller(LocalDate date) {
		Route r = getRoute(date);
		return r == null ? null : r.getSeller(date);
	}
}
