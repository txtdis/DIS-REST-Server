package ph.txtdis.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractDated implements Serializable {

	private static final long serialVersionUID = -8816070253252272183L;

	@Id
	@Column(updatable = false)
	protected LocalDate id;
}