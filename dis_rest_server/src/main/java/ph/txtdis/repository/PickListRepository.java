package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.PickList;

@Repository("pickingRepository")
public interface PickListRepository extends SpunRepository<PickList, Long> {

	PickList findByBookingsId(@Param("bookingId") Long id);

	List<PickList> findByPickDate(LocalDate date);
}
