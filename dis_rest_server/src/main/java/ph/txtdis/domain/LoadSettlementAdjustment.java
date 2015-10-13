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
public class LoadSettlementAdjustment extends AuditedId {

	private static final long serialVersionUID = 9083329396715031474L;

	@Column(nullable = false)
	private LocalDate pickDate;

	@ManyToOne(optional = false)
	private Truck truck;

	@ManyToOne(optional = false)
	private Item item;

	@Column(nullable = false)
	private String actionTaken;
}
