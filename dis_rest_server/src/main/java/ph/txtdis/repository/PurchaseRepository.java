package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Purchase;

@Repository("PurchaseRepository")
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
