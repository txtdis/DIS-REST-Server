package ph.txtdis.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(
//@formatter:off
	indexes = {
		@Index(columnList = "prefix, startId, endId, suffix") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "prefix", "startId", "endId", "suffix" }) )
//@formatter:on
public class InvoiceBooklet extends TrackedId {

	private static final long serialVersionUID = 6045289585003677813L;

	private String prefix, suffix;

	private long startId, endId;

	@ManyToOne(optional = false)
	private User issuedTo;
}
