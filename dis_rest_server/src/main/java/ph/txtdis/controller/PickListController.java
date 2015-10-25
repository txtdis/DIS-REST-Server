package ph.txtdis.controller;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Booking;
import ph.txtdis.domain.PickList;
import ph.txtdis.exception.FailedPrintingException;
import ph.txtdis.printer.LoadOrderPrinter;
import ph.txtdis.printer.Printed;
import ph.txtdis.printer.SalesOrderPrinter;
import ph.txtdis.repository.BookingRepository;
import ph.txtdis.repository.PickListRepository;
import ph.txtdis.util.Spring;

@RestController("pickListController")
@RequestMapping("/pickLists")
public class PickListController extends SpunController<PickListRepository, PickList, Long> {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private LoadOrderPrinter loadOrderPrinter;

	@Autowired
	private SalesOrderPrinter salesOrderPrinter;

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/booking", method = RequestMethod.GET)
	public ResponseEntity<?> findByBooking(@RequestParam("id") Long id) {
		PickList t = repository.findByBookingsId(id);
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@RequestMapping(path = "/date", method = RequestMethod.GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<PickList> l = repository.findByPickDate(date);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<PickList> l = repository.findAll();
		return new ResponseEntity<>(l, HttpStatus.OK);
	}

	@RequestMapping(path = "/print", method = RequestMethod.GET)
	public ResponseEntity<?> print(@RequestParam("id") Long id) throws Exception {
		PickList t = repository.findOne(id);
		try {
			t = printLoadAndSalesOrder(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedPrintingException(e.getMessage());
		}
		return new ResponseEntity<>(t, HttpStatus.OK);
	}

	private PickList printLoadAndSalesOrder(PickList t) throws Exception {
		loadOrderPrinter.print(t);
		printSalesOrders(t);
		savePrintData(t);
		return repository.save(t);
	}

	private void printSalesOrder(Booking t) throws Exception {
		salesOrderPrinter.print(t);
		savePrintData(t);
		bookingRepository.save(t);
	}

	private void printSalesOrders(PickList p) throws Exception {
		List<Booking> c = p.getBookings().stream().filter(b -> b.getPrintedBy() == null).collect(Collectors.toList());
		for (Booking t : c)
			printSalesOrder(t);
	}

	private void savePrintData(Printed t) {
		t.setPrintedBy(Spring.user().getUsername());
		t.setPrintedOn(ZonedDateTime.now());
	}
}