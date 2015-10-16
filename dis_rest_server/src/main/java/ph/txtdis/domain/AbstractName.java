package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractName extends TrackedId {

	private static final long serialVersionUID = -5859599323217964344L;

	@Column(nullable = false, unique = true)
	protected String name;

	@Override
	public String toString() {
		return name;
	}
}
