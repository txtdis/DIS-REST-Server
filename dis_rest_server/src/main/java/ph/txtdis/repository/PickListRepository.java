package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Picking;

@Repository("pickingRepository")
public interface PickListRepository extends SpunRepository<Picking, Long> {

	Picking findByBookingsBookingId(@Param("bookingId") Long id);

	List<Picking> findByPickDate(LocalDate date);
}
