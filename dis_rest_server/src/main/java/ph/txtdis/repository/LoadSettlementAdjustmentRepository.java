package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.LoadSettlementAdjustment;

@Repository("loadSettlementAdjustmentRepository")
public interface LoadSettlementAdjustmentRepository extends CrudRepository<LoadSettlementAdjustment, Long> {
}
