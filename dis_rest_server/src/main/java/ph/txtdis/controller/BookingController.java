package ph.txtdis.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ph.txtdis.util.DateTimeUtils.endOfDay;
import static ph.txtdis.util.DateTimeUtils.startOfDay;

import ph.txtdis.domain.Billing;
import ph.txtdis.dto.Billable;
import ph.txtdis.repository.BillingRepository;
import ph.txtdis.service.BillingToBillableConverter;

@RestController("bookingController")
@RequestMapping("/bookings")
public class BookingController extends IdController<BillingRepository, Billing, Long> {

	@Autowired
	private BillingToBillableConverter fromBilling;

	@RequestMapping(path = "/date", method = GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") Date d) {
		ZonedDateTime start = startOfDay(d.toLocalDate());
		ZonedDateTime end = endOfDay(d.toLocalDate());
		Billing b = repository.findFirstByBookingIdNotNullAndCreatedOnBetweenOrderByCreatedOnAsc(start, end);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/pick", method = GET)
	public ResponseEntity<?> findByPickDate(@RequestParam("date") Date d) {
		List<Billing> l = repository.findByOrderDate(d.toLocalDate());
		return new ResponseEntity<>(l, OK);
	}

	@RequestMapping(path = "/first", method = GET)
	public ResponseEntity<?> first() {
		Billing b = repository.findFirstByBookingIdNotNullOrderByBookingIdAsc();
		Billable i = fromBilling.toBillable(b);
		setBookingIdAsBillableIdForSpinning(i);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/last", method = GET)
	public ResponseEntity<?> last() {
		Billing b = repository.findFirstByBookingIdNotNullOrderByBookingIdDesc();
		Billable i = fromBilling.toBillable(b);
		setBookingIdAsBillableIdForSpinning(i);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/next", method = GET)
	public ResponseEntity<?> next(@RequestParam("id") Long id) {
		Billing b = repository.findFirstByBookingIdNotNullAndBookingIdGreaterThanOrderByBookingIdAsc(id);
		Billable i = fromBilling.toBillable(b);
		i.setId(i.getBookingId());
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/previous", method = GET)
	public ResponseEntity<?> previous(@RequestParam("id") Long id) {
		Billing b = repository.findFirstByBookingIdNotNullAndBookingIdLessThanOrderByBookingIdDesc(id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	private void setBookingIdAsBillableIdForSpinning(Billable i) {
		i.setId(i.getBookingId());
	}
}