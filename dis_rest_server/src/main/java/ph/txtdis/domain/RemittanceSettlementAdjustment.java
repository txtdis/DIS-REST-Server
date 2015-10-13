package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class RemittanceSettlementAdjustment extends AuditedId {

	private static final long serialVersionUID = 4362180227772097988L;

	@Column(nullable = false)
	private LocalDate pickDate;

	@ManyToOne(optional = false)
	private Truck truck;

	@ManyToOne(optional = false)
	private Invoice invoice;

	@Column(nullable = false)
	private String actionTaken;
}
