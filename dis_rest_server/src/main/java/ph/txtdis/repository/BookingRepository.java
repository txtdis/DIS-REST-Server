package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends SpunRepository<Booking, Long> {

	List<Booking> findByOrderDate(LocalDate d);
}
