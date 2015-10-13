package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.RemittanceSettlementAdjustment;

@Repository("remitttanceSettlementAdjustmentRepository")
public interface RemitttanceSettlementAdjustmentRepository
		extends CrudRepository<RemittanceSettlementAdjustment, Long>
{
}
