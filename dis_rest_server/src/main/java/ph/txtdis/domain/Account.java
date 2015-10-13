package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "route_id", "startDate" }) )
public class Account extends AuditedId implements Comparable<Account> {

	private static final long serialVersionUID = -3816774251745575218L;

	@Column(nullable = false)
	private String seller;

	@Column(nullable = false)
	private LocalDate startDate;

	@Override
	public int compareTo(Account a) {
		return getStartDate().compareTo(a.getStartDate());
	}
}
