package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.RemittanceDetail;

@Repository("remittanceDetailRepository")
public interface RemittanceDetailRepository extends CrudRepository<RemittanceDetail, Long> {

	List<RemittanceDetail> findByBilling(@Param("billing") Billing b);
}
