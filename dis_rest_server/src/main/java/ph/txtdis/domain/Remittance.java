package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(indexes = {
// @formatter:off
		@Index(columnList = "payment_date"),
		@Index(columnList = "drawee_bank_id, check_id") })
// @formatter:on
public class Remittance extends TrackedOrder {

	private static final long serialVersionUID = -5860334462169889589L;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal value;

	@Column(name = "is_valid")
	private Boolean isValid;

	@ManyToOne
	@JoinColumn(name = "drawee_bank_id")
	private Customer draweeBank;

	@ManyToOne
	@JoinColumn(name = "depositor_bank_id")
	private Customer depositorBank;

	@ManyToOne
	private Customer payor;

	@JoinColumn(name = "remittance_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<RemittanceDetail> details;

	@Column(name = "payment_date", nullable = false)
	private LocalDate paymentDate;

	@Column(name = "check_id")
	private Long checkId;

	private String collector, remarks, depositor;

	@Column(name = "account_id")
	private String accountNo;

	@Column(name = "received_by")
	private String receivedBy;

	@Column(name = "audited_by")
	private String auditedBy;

	@Column(name = "deposited_on")
	private ZonedDateTime depositedOn;

	@Column(name = "received_on")
	private ZonedDateTime receivedOn;

	@Column(name = "audited_on")
	private ZonedDateTime auditedOn;

	@Column(name = "depositor_on")
	private ZonedDateTime depositorOn;

	@Override
	public String toString() {
		return "Collection ID " + id;
	}
}
