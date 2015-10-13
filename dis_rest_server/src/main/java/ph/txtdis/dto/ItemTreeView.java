package ph.txtdis.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.ItemFamily;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("with recursive p (family_id, parent_id) as ( "
        + "   select t.family_id, t.parent_id from item_tree t union all "
        + "   select p.family_id, t.parent_id from item_tree t join p on t.family_id = p.parent_id "
        + " ) select * from p ")
@Synchronize({ "item_tree" })
public class ItemTreeView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private ItemFamily family;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private ItemFamily parent;

    @Override
    public String toString() {
        return family + " is a/n " + parent;
    }
}
