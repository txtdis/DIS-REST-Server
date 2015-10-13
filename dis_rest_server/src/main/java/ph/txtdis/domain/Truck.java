package ph.txtdis.domain;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Truck extends AbstractName {

    private static final long serialVersionUID = -8355723680264208431L;

    public Truck(String name) {
        super(name);
    }
}
