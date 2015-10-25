package ph.txtdis.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Receiving;

@Repository("receivingRepository")
public interface ReceivingRepository extends SpunRepository<Receiving, Long> {

	Receiving findByReferenceId(@Param("refId") Long refId);
}
