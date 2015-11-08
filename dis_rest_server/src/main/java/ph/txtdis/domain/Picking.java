package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.printer.Printed;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picking extends TrackedOrder implements Printed {

	private static final long serialVersionUID = -3835242947594550479L;

	@ManyToOne(optional = false)
	private Truck truck;

	@ManyToOne(optional = false)
	private User driver;

	@ManyToOne(optional = false)
	@JoinColumn(name = "lead_helper")
	private User leadHelper;

	@ManyToOne
	@JoinColumn(name = "asst_helper")
	private User asstHelper;

	@Column(name = "pick_date", nullable = false)
	private LocalDate pickDate;

	private String remarks;

	@OneToMany
	@JoinColumn(name = "pick_list_id")
	private List<Billing> bookings;

	@Column(name = "printed_by")
	private String printedBy;

	@Column(name = "printed_on")
	private ZonedDateTime printedOn;

	@Column(name = "is_printed")
	private Boolean printedAll;

	public Picking(Truck truck, User driver, User leadHelper, LocalDate pickDate) {
		this.truck = truck;
		this.driver = driver;
		this.leadHelper = leadHelper;
		this.pickDate = pickDate;
	}
}
