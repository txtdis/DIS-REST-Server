package ph.txtdis.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Picking;

@Repository("pickingRepository")
public interface PickingRepository extends SpunRepository<Picking, Long> {

	Picking findByBookingsId(@Param("bookingId") Long id);
}
