package ph.txtdis.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
//@formatter:off
@Table(
	indexes = {
		@Index(columnList = "family_id, parent_id"),
		@Index(columnList = "family_id") },
	uniqueConstraints =
		@UniqueConstraint(columnNames = { "family_id", "parent_id" }) )
//@formatter:on
public class ItemTree extends TrackedId {

	private static final long serialVersionUID = 4058968729625611538L;

	@ManyToOne(optional = false)
	private ItemFamily family;

	@ManyToOne(optional = false)
	private ItemFamily parent;

	@Override
	public String toString() {
		return family + " member of " + parent;
	}
}
