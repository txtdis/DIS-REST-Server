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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picking extends AuditedId {

	private static final long serialVersionUID = -3835242947594550479L;

	@ManyToOne(optional = false)
	private Truck truck;

	@ManyToOne(optional = false)
	private User driver;

	@ManyToOne(optional = false)
	private User helper1;

	@ManyToOne
	private User helper2;

	@Column(nullable = false)
	private LocalDate pickDate;

	private String remarks;

	@OneToMany
	@JoinColumn(name = "picking_id")
	private List<Booking> bookings;

	@ManyToOne
	private User printedBy;

	private ZonedDateTime printedOn;

	public Picking(Truck truck, User driver, User helper1, LocalDate pickDate) {
		this.truck = truck;
		this.driver = driver;
		this.helper1 = helper1;
		this.pickDate = pickDate;
	}
}
