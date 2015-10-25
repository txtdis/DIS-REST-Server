package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@Table(name = "remittance_settlement_adjustment")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemittanceSettlementAdjustment extends TrackedOrder {

	private static final long serialVersionUID = 4362180227772097988L;

	@Column(name = "pick_date", nullable = false)
	private LocalDate pickDate;

	@ManyToOne(optional = false)
	private Truck truck;

	@ManyToOne(optional = false)
	private Invoice invoice;

	@Column(name = "action_taken", nullable = false)
	private String actionTaken;
}
