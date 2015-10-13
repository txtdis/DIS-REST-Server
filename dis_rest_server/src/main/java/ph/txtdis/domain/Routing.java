package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id", "startDate" }) )
public class Routing extends AuditedId implements Comparable<Routing> {

	private static final long serialVersionUID = -4540897080828317375L;

	@ManyToOne(optional = false)
	private Route route;

	@Column(nullable = false)
	private LocalDate startDate;

	@Override
	public int compareTo(Routing routing) {
		return getStartDate().compareTo(routing.getStartDate());
	}
}
