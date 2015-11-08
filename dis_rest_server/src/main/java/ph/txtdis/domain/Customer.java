package ph.txtdis.domain;

import static java.time.LocalDate.now;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(indexes = { @Index(columnList = "name"), @Index(columnList = "deactivated_on, type") })
public class Customer extends AbstractDeactivatedName {

	private static final long serialVersionUID = -878749889584633340L;

	private String street;

	@ManyToOne(optional = false)
	private Location barangay;

	@ManyToOne(optional = false)
	private Location city;

	@ManyToOne(optional = false)
	private Location province;

	@ManyToOne
	@JoinColumn(name = "primary_pricing")
	private PricingType primaryPricingType;

	@ManyToOne
	@JoinColumn(name = "alternate_pricing")
	private PricingType alternatePricingType;

	private CustomerType type;

	@ManyToOne
	private Channel channel;

	@Column(name = "visit_frequency")
	private VisitFrequency visitFrequency;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Routing> routeHistory;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "contact_surname")
	private String contactSurname;

	@Column(name = "contact_title")
	private String contactTitle;

	private String mobile;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<CreditDetail> creditDetails;

	@JoinColumn(name = "customer_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Discount> discounts;

	@ManyToOne
	private Customer parent;

	public Customer(String name, CustomerType type) {
		super(name);
		this.type = type;
	}

	@JsonIgnore
	public String getAddress() {
		return street() + barangay() + city() + province();
	}

	@JsonIgnore
	public String getSeller() {
		try {
			return getRoute().getSeller(now());
		} catch (Exception e) {
			return null;
		}
	}

	private String barangay() {
		if (barangay == null)
			return "";
		return (street != null ? ", " : "") + barangay;
	}

	private String city() {
		if (city == null)
			return "";
		return (barangay != null || street != null ? ", " : "") + city;
	}

	private Route getRoute() {
		try {
			return getRouteHistory().stream().filter(p -> !p.getStartDate().isAfter(now()))
					.max((a, b) -> a.getStartDate().compareTo(b.getStartDate())).get().getRoute();
		} catch (Exception e) {
			return null;
		}
	}

	private String province() {
		if (province == null)
			return "";
		return (city != null || barangay != null || street != null ? ", " : "") + province;
	}

	private String street() {
		return street == null ? "" : street;
	}
}
