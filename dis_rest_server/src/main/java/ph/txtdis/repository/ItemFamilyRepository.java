package ph.txtdis.repository;

import org.springframework.stereotype.Repository;

import ph.txtdis.domain.ItemFamily;

@Repository("itemFamilyRepository")
public interface ItemFamilyRepository extends NameListRepository<ItemFamily> {
}