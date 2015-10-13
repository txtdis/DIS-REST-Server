package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Booking;
import ph.txtdis.repository.BookingRepository;

@RestController("bookingController")
@RequestMapping("/bookings")
public class BookingController extends SpunController<BookingRepository, Booking, Long> {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Booking> bookings = repository.findAll();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}
}