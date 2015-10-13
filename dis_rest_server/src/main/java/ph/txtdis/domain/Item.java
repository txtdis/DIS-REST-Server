package ph.txtdis.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.ItemType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = { @Index(columnList = "name") })
public class Item extends AbstractDeactivatedName {

	private static final long serialVersionUID = -3012020260825126952L;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private ItemType type;

	@ManyToOne
	private ItemFamily family;

	private String vendorId;

	private boolean notDiscounted;

	@JoinColumn(name = "item_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<QtyPerUom> qtyPerUomList;

	@JoinColumn(name = "item_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Price> priceList;

	@JoinColumn(name = "item_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<VolumeDiscount> volumeDiscounts;

	@JoinColumn(name = "item_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bom> boms;

	public Item(String name, String description, ItemType type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}
}
