package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "invoice_booklet",
		uniqueConstraints = @UniqueConstraint(columnNames = { "prefix", "start_id", "end_id", "suffix" }) ,
		indexes = { @Index(columnList = "prefix, start_id, end_id, suffix") })
public class InvoiceBooklet extends TrackedOrder {

	private static final long serialVersionUID = 6045289585003677813L;

	private String prefix, suffix;

	@Column(name = "start_id")
	private long startId;

	@Column(name = "end_id")
	private long endId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "issued_to")
	private User issuedTo;
}
