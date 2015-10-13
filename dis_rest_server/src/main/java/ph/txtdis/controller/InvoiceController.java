package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Booking;
import ph.txtdis.domain.Invoice;
import ph.txtdis.repository.InvoiceRepository;

@RestController("invoiceController")
@RequestMapping("/invoices")
public class InvoiceController extends SpunController<InvoiceRepository, Invoice, Long> {

	@RequestMapping(path = "/booking", method = RequestMethod.GET)
	public ResponseEntity<?> findByBooking(@RequestParam("id") Booking b) {
		Invoice invoice = repository.findByBooking(b);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@RequestParam("prefix") String prefix, @RequestParam("id") Long id,
			@RequestParam("suffix") String suffix) {
		if (prefix.isEmpty())
			prefix = null;
		if (suffix.isEmpty())
			suffix = null;
		Invoice entity = repository.findByIdPrefixAndIdNoAndIdSuffix(prefix, id, suffix);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Invoice> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(path = "/latest", method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam("prefix") String prefix, @RequestParam("suffix") String suffix,
			@RequestParam("start") Long start, @RequestParam("end") Long end) {
		Invoice invoice = repository.findFirstByIdPrefixAndIdSuffixAndIdNoBetweenOrderByIdNoDesc(prefix, suffix, start,
				end);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}
}