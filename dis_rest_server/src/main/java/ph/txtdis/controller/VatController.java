package ph.txtdis.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Invoice;
import ph.txtdis.dto.Vat;
import ph.txtdis.repository.InvoiceRepository;

@RestController("vatController")
@RequestMapping("/vats")
public class VatController {

	@Autowired
	InvoiceRepository repository;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam Date start, @RequestParam Date end) {
		LocalDate first = start.toLocalDate();
		LocalDate last = end.toLocalDate();
		List<Invoice> invoices = repository.findByOrderDateBetweenOrderByOrderDateAsc(first, last);
		List<Vat> list = invoices == null ? null : invoices.stream().map(i -> new Vat(i)).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(path = "/rate", method = RequestMethod.GET)
	public ResponseEntity<?> vatRate() {
		return new ResponseEntity<>(new Vat(), HttpStatus.OK);
	}
}