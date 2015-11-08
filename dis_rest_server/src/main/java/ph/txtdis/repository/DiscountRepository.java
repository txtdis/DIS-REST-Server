package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Discount;

@Repository("discountRepository")
public interface DiscountRepository extends CrudRepository<Discount, Long> {
}
