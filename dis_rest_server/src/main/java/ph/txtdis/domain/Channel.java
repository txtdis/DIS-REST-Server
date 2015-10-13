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
public class Channel extends AbstractName implements Comparable<Channel> {

    private static final long serialVersionUID = -8012814058038917889L;

    public Channel(String name) {
        super(name);
    }

    @Override
    public int compareTo(Channel o) {
        return toString().compareTo(o.toString());
    }
}