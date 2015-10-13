package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.LocationType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = { @Index(columnList = "type, name") })
public class Location extends AbstractName {

	private static final long serialVersionUID = -9066269489440543721L;

	@Column(nullable = false)
	private LocationType type;

	public Location(String name, LocationType type) {
		super(name);
		this.type = type;
	}
}
