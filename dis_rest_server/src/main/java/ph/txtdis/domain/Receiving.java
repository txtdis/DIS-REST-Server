package ph.txtdis.domain;

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
public class Receiving extends NumberedOrder {

	private static final long serialVersionUID = 1717512766392903432L;

	@Column(name = "sender_ref_id")
	private String senderReferenceId;

	@Column(name = "ref_id", nullable = false)
	private Long referenceId;

	@ManyToOne(optional = false)
	private User checker;

	@JoinColumn(name = "receiving_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<SoldDetail> details;

	public Receiving(Customer customer, Long referenceId, User checker, LocalDate orderDate) {
		this.customer = customer;
		this.referenceId = referenceId;
		this.checker = checker;
		this.orderDate = orderDate;
	}
}
