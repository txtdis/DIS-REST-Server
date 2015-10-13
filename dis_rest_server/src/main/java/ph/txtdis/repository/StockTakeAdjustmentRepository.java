package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.StockTakeAdjustment;

@Repository("stockTakeAdjustmentRepository")
public interface StockTakeAdjustmentRepository extends CrudRepository<StockTakeAdjustment, Long> {
}
