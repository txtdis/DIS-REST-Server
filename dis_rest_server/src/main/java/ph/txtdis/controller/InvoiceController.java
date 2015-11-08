package ph.txtdis.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

import java.net.URI;
import java.sql.Date;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ph.txtdis.util.DateTimeUtils.endOfDay;
import static ph.txtdis.util.DateTimeUtils.startOfDay;

import ph.txtdis.domain.Billing;
import ph.txtdis.dto.Billable;
import ph.txtdis.repository.BillingRepository;
import ph.txtdis.service.BillableToBillingConverter;
import ph.txtdis.service.BillingToBillableConverter;

@RestController("invoiceController")
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private BillingToBillableConverter fromBilling;

	@Autowired
	private BillableToBillingConverter fromBillable;

	@Autowired
	private BillingRepository repository;

	@RequestMapping(path = "/{id}", method = GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Billing b = repository.findOne(id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/booking", method = GET)
	public ResponseEntity<?> findBookingById(@RequestParam("id") Long id) {
		Billing b = repository.findFirstByBookingId(id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/referenced", method = GET)
	public ResponseEntity<?> findByBookingId(@RequestParam("bookingId") Long id) {
		Billing b = repository.findFirstByBookingId(id);
		Billable i = idOnlyInvoice(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/date", method = GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") Date d) {
		ZonedDateTime start = startOfDay(d.toLocalDate());
		ZonedDateTime end = endOfDay(d.toLocalDate());
		Billing b = repository.findFirstByNumIdNotNullAndNumIdGreaterThanAndCreatedOnBetweenOrderByCreatedOnAsc(0L,
				start, end);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/find", method = GET)
	public ResponseEntity<?> findByOrderNo(@RequestParam("prefix") String p, @RequestParam("id") Long id,
			@RequestParam("suffix") String s) {
		Billing b = repository.findByPrefixAndSuffixAndNumId(nullIfEmpty(p), nullIfEmpty(s), id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/first", method = GET)
	public ResponseEntity<?> first() {
		Billing b = repository.findFirstByNumIdNotNullAndNumIdGreaterThanOrderByIdAsc(0L);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/last", method = GET)
	public ResponseEntity<?> last() {
		Billing b = repository.findFirstByNumIdNotNullAndNumIdGreaterThanOrderByIdDesc(0L);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/latest", method = GET)
	public ResponseEntity<?> list(@RequestParam("prefix") String prefix, @RequestParam("suffix") String suffix,
			@RequestParam("start") Long start, @RequestParam("end") Long end) {
		Billing b = repository.findFirstByPrefixAndSuffixAndNumIdBetweenOrderByNumIdDesc(nullIfEmpty(prefix),
				nullIfEmpty(suffix), start, end);
		Billable i = idOnlyInvoice(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/next", method = GET)
	public ResponseEntity<?> next(@RequestParam("id") Long id) {
		Billing b = repository.findFirstByNumIdNotNullAndNumIdGreaterThanAndIdGreaterThanOrderByIdAsc(0L, id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(path = "/previous", method = GET)
	public ResponseEntity<?> previous(@RequestParam("id") Long id) {
		Billing b = repository.findFirstByNumIdNotNullAndNumIdGreaterThanAndIdLessThanOrderByIdDesc(0L, id);
		Billable i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<?> save(@RequestBody Billable i) {
		Billing b = fromBillable.toBilling(i);
		b = repository.save(b);
		i = fromBilling.toBillable(b);
		return new ResponseEntity<>(i, httpHeaders(i), CREATED);
	}

	private MultiValueMap<String, String> httpHeaders(Billable i) {
		URI uri = fromCurrentContextPath().path("/{id}").buildAndExpand(i.getId()).toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return httpHeaders;
	}

	private Billable idOnlyInvoice(Billing b) {
		Billable i = new Billable();
		i.setPrefix(b.getPrefix());
		i.setNumId(b.getNumId());
		i.setSuffix(b.getSuffix());
		return i;
	}

	private String nullIfEmpty(String s) {
		return s.isEmpty() ? null : s;
	}
}