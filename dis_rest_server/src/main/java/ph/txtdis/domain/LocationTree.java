package ph.txtdis.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class LocationTree extends AuditedId {

    private static final long serialVersionUID = 7452722249369420815L;

    @ManyToOne(optional = false)
    private Location location;

    @ManyToOne(optional = false)
    private Location parent;
}
