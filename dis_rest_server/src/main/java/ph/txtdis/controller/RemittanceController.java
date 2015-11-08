package ph.txtdis.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

import java.net.URI;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Remittance;
import ph.txtdis.dto.Payment;
import ph.txtdis.repository.RemittanceRepository;
import ph.txtdis.service.PaymentToRemittanceConverter;
import ph.txtdis.service.RemittanceToPaymentConverter;

@RestController("remittanceController")
@RequestMapping("/remittances")
public class RemittanceController {

	@Autowired
	private RemittanceToPaymentConverter fromRemittance;

	@Autowired
	private PaymentToRemittanceConverter fromPayment;

	@Autowired
	private RemittanceRepository repository;

	@RequestMapping(path = "/{id}", method = GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Remittance r = repository.findOne(id);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/check", method = GET)
	public ResponseEntity<?> findByCheck(@RequestParam("bank") Customer b, @RequestParam("id") Long id) {
		Remittance r = repository.findByDraweeBankAndCheckId(b, id);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/date", method = GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") Date d) {
		Remittance r = repository.findFirstByPaymentDateOrderByIdAsc(d.toLocalDate());
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/first", method = GET)
	public ResponseEntity<?> first() {
		Remittance r = repository.findFirstByOrderByIdAsc();
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/last", method = GET)
	public ResponseEntity<?> last() {
		Remittance r = repository.findFirstByOrderByIdDesc();
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/next", method = GET)
	public ResponseEntity<?> next(@RequestParam("id") Long id) {
		Remittance r = repository.findFirstByIdGreaterThanOrderByIdAsc(id);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/previous", method = GET)
	public ResponseEntity<?> previous(@RequestParam("id") Long id) {
		Remittance r = repository.findFirstByIdLessThanOrderByIdDesc(id);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<?> save(@RequestBody Payment p) {
		Remittance r = fromPayment.toRemittance(p);
		r = repository.save(r);
		p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, httpHeaders(p), CREATED);
	}

	private MultiValueMap<String, String> httpHeaders(Payment p) {
		URI uri = fromCurrentContextPath().path("/{id}").buildAndExpand(p.getId()).toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return httpHeaders;
	}
}