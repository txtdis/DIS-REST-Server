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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.ReceivingReferenceType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Receipt extends NumberedOrder {

	private static final long serialVersionUID = 1717512766392903432L;

	private String partnerReferenceId;

	@Column(nullable = false)
	private ReceivingReferenceType reference;

	@Column(nullable = false)
	private Long referenceId;

	@ManyToOne(optional = false)
	private User checker;

	@JoinColumn(name = "receipt_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<ReceiptDetail> details;

	public Receipt(Customer customer, ReceivingReferenceType reference, Long referenceId, User checker,
			LocalDate orderDate) {
		this.customer = customer;
		this.reference = reference;
		this.referenceId = referenceId;
		this.checker = checker;
		this.orderDate = orderDate;
	}
}
