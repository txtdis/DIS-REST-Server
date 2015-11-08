package ph.txtdis.controller;

import static java.time.ZonedDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static ph.txtdis.util.SpringUtils.username;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Picking;
import ph.txtdis.exception.FailedPrintingException;
import ph.txtdis.printer.LoadOrderPrinter;
import ph.txtdis.printer.SalesOrderPrinter;
import ph.txtdis.repository.PickListRepository;

@RestController("pickListController")
@RequestMapping("/pickLists")
public class PickListController extends SpunController<PickListRepository, Picking, Long> {

	@Autowired
	private LoadOrderPrinter loadOrderPrinter;

	@Autowired
	private SalesOrderPrinter salesOrderPrinter;

	@RequestMapping(path = "/booking", method = GET)
	public ResponseEntity<?> findByBooking(@RequestParam("id") Long id) {
		Picking t = repository.findByBookingsBookingId(id);
		return new ResponseEntity<>(t, OK);
	}

	@RequestMapping(path = "/date", method = GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") Date on) {
		List<Picking> l = repository.findByPickDate(on.toLocalDate());
		return new ResponseEntity<>(l, OK);
	}

	@RequestMapping(path = "/print", method = GET)
	public ResponseEntity<?> print(@RequestParam("id") Long id) throws Exception {
		Picking t = repository.findOne(id);
		try {
			t = printLoadAndSalesOrder(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedPrintingException(e.getMessage());
		}
		return new ResponseEntity<>(t, OK);
	}

	private Picking printLoadAndSalesOrder(Picking t) throws Exception {
		loadOrderPrinter.print(t);
		printSalesOrders(t);
		savePrintData(t);
		return repository.save(t);
	}

	private void printSalesOrder(Billing t) throws Exception {
		salesOrderPrinter.print(t);
		t.setPrinted(true);
	}

	private void printSalesOrders(Picking p) throws Exception {
		List<Billing> c = p.getBookings().stream().filter(b -> b.getPrinted() == null).collect(toList());
		for (Billing t : c)
			printSalesOrder(t);
	}

	private void savePrintData(Picking t) {
		t.setPrintedBy(username());
		t.setPrintedOn(now());
		t.setPrintedAll(true);
	}
}