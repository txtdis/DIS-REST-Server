package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Remittance extends TrackedOrder {

	private static final long serialVersionUID = -5860334462169889589L;

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate;

	@Column(name = "check_id")
	private Long checkId;

	@Column(name = "drawee_bank", nullable = false)
	private String draweeBank;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal value;

	@Column(nullable = false)
	private String payor;

	private String remarks;

	@Column(name = "received_by")
	private String receivedBy;

	@Column(name = "received_on")
	private ZonedDateTime receivedOn;

	@Column(name = "deposit_bank")
	private String depositBank;

	@Column(name = "deposit_date")
	private LocalDate depositDate;

	@Column(name = "deposit_time")
	private LocalTime depositTime;

	@JoinColumn(name = "remittance_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<RemittanceDetail> details;

	@LastModifiedBy
	@Column(name = "last_modified_by", nullable = false)
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_on", nullable = false)
	private ZonedDateTime lastModifiedOn;

	public Remittance(LocalDate dueDate, String draweeBank, String payor, Long checkId, BigDecimal value) {
		this.dueDate = dueDate;
		this.draweeBank = draweeBank;
		this.payor = payor;
		this.checkId = checkId;
		this.value = value;
	}
}
