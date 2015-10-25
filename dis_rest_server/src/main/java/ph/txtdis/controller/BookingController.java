package ph.txtdis.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Booking;
import ph.txtdis.repository.BookingRepository;

@RestController("bookingController")
@RequestMapping("/bookings")
public class BookingController extends SpunController<BookingRepository, Booking, Long> {

	@RequestMapping(path = "/pick", method = RequestMethod.GET)
	public ResponseEntity<?> findByPickDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d) {
		List<Booking> l = repository.findByOrderDate(d);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Booking> l = repository.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
}