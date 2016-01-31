package ph.txtdis.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static lombok.AccessLevel.PROTECTED;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractId<PK> implements Keyed<PK>, Serializable {

	private static final long serialVersionUID = 6503580333612358526L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	protected PK id;
}