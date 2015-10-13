package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.UserType;

@Data
@Entity
@AllArgsConstructor
@Table(name = "authorities")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends AbstractId {

    private static final long serialVersionUID = 4772261079413536002L;

    @Column(nullable = false)
    private UserType authority;
}
