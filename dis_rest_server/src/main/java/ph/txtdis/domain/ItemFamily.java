package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.ItemTier;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_family", indexes = { @Index(columnList = "name") })
public class ItemFamily extends AbstractName {

	private static final long serialVersionUID = 1590751261303247800L;

	@Column(nullable = false)
	private ItemTier tier;

	public ItemFamily(String name, ItemTier tier) {
		super(name);
		this.tier = tier;
	}
}
