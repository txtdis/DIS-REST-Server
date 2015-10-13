package ph.txtdis.repository;

import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends SpunRepository<Booking, Long> {
}
