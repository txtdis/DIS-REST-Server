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
//@formatter:off
@Table(
	indexes = {
		@Index(columnList = "idPrefix, startId, endId, idSuffix") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "idPrefix", "startId", "endId", "idSuffix" }) )
//@formatter:on
public class InvoiceBooklet extends AuditedId {

	private static final long serialVersionUID = 6045289585003677813L;

	private String idPrefix, idSuffix;

	private long startId, endId;

	@ManyToOne(optional = false)
	private User issuedTo;
}
