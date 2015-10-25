package ph.txtdis.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.printer.Printed;

@Data
@Entity
@AllArgsConstructor
@Table(name = "pick_list")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PickList extends TrackedOrder implements Printed {

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
	private List<Booking> bookings;

	@Column(name = "printed_by")
	private String printedBy;

	@Column(name = "printed_on")
	private ZonedDateTime printedOn;

	public PickList(Truck truck, User driver, User leadHelper, LocalDate pickDate) {
		this.truck = truck;
		this.driver = driver;
		this.leadHelper = leadHelper;
		this.pickDate = pickDate;
	}
}
