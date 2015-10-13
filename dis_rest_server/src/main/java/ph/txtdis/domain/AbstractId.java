package ph.txtdis.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractId implements Keyed<Long>, Serializable {

    private static final long serialVersionUID = 6503580333612358526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}