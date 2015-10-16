package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Remittance extends TrackedId {

	private static final long serialVersionUID = -5860334462169889589L;

	@Column(nullable = false)
	private LocalDate remitDate;

	@ManyToOne(optional = false)
	private Customer bank;

	@Column(nullable = false)
	private String reference;

	@Column(nullable = false)
	private BigDecimal value;

	@ManyToOne(optional = false)
	private User collector;

	private String remarks;

	@JoinColumn(name = "remittance_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<RemittanceDetail> details;

	public Remittance(LocalDate remitDate, Customer bank, String reference, BigDecimal value, User collector) {
		this.remitDate = remitDate;
		this.bank = bank;
		this.reference = reference;
		this.value = value;
		this.collector = collector;
	}

	@Override
	public String toString() {
		return "R/S No. " + getId();
	}
}
