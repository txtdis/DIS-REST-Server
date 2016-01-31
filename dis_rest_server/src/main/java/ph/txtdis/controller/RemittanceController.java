package ph.txtdis.controller;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static ph.txtdis.type.PaymentType.CASH;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import ph.txtdis.converter.PaymentToRemittanceConverter;
import ph.txtdis.converter.RemittanceToPaymentConverter;
import ph.txtdis.domain.Billing;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Remittance;
import ph.txtdis.dto.Payment;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.RemittanceRepository;
import ph.txtdis.type.PaymentType;

@RestController("remittanceController")
@RequestMapping("/remittances")
public class RemittanceController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RemittanceToPaymentConverter fromRemittance;

	@Autowired
	private PaymentToRemittanceConverter fromPayment;

	@Autowired
	private RemittanceRepository repository;

	@Value("${go.live}")
	private String goLive;

	@Value("${grace.period.cash.deposit}")
	private String gracePeriodCashDeposit;

	@Value("${grace.period.check.deposit}")
	private String gracePeriodCheckDeposit;

	@Value("${vendor.id}")
	private String vendorId;

	private Customer vendor;

	@RequestMapping(path = "/{id}", method = GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Remittance r = repository.findOne(id);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/find", method = GET)
	public ResponseEntity<?> findByBilling(@RequestParam("billing") Billing b) {
		List<Remittance> r = repository.findByDetailsBilling(b);
		List<Payment> p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/check", method = GET)
	public ResponseEntity<?> findByCheck(@RequestParam("bank") Customer b, @RequestParam("id") Long id) {
		Remittance r = repository.findByDraweeBankAndCheckIdAndIsValidIn(b, id, asList(null, false));
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/collector", method = GET)
	public ResponseEntity<?> findByCollector(@RequestParam("name") String n, @RequestParam("date") Date d) {
		Remittance r = repository.findFirstByCollectorAndPaymentDateAndCheckIdNull(n, d.toLocalDate());
		Payment p = idOnlyPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/date", method = GET)
	public ResponseEntity<?> findByDate(@RequestParam("on") Date d) {
		ZonedDateTime start = startOfDay(d.toLocalDate());
		ZonedDateTime end = endOfDay(d.toLocalDate());
		Remittance r = repository.findFirstByCreatedOnBetweenOrderByIdAsc(start, end);
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/undeposited", method = GET)
	public ResponseEntity<?> findByUndepositedPayments(@RequestParam("payType") PaymentType t,
			@RequestParam("seller") String s, @RequestParam("upTo") Date d) {
		Remittance r = findOneUndepositedPayment(t, s, d.toLocalDate());
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/first", method = GET)
	public ResponseEntity<?> first() {
		Remittance r = repository.findFirstByOrderByIdAsc();
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/firstToSpin", method = GET)
	public ResponseEntity<?> firstToSpin() {
		Remittance r = repository.findFirstByOrderByIdAsc();
		Payment p = idOnlyPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/last", method = GET)
	public ResponseEntity<?> last() {
		Remittance r = repository.findFirstByOrderByIdDesc();
		Payment p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(path = "/lastToSpin", method = GET)
	public ResponseEntity<?> lastToSpin() {
		Remittance r = repository.findFirstByOrderByIdDesc();
		Payment p = idOnlyPayment(r);
		return new ResponseEntity<>(p, OK);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<?> list() {
		// TODO return minus days back to 15
		ZonedDateTime twoWeeksAgo = endOfDay(LocalDate.now().minusDays(60L));
		List<Remittance> lr = repository.findByCreatedOnGreaterThanOrderByIdDesc(twoWeeksAgo);
		List<Payment> p = lr.stream().map(r -> fromRemittance.toForHistoryPayment(r)).collect(Collectors.toList());
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

	@RequestMapping(path = "/all", method = POST)
	public ResponseEntity<?> save(@RequestBody List<Payment> p) {
		List<Remittance> r = fromPayment.toRemittance(p);
		if (repository.save(r) == null)
			p = null;
		return new ResponseEntity<>(p, CREATED);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<?> save(@RequestBody Payment p) {
		Remittance r = fromPayment.toRemittance(p);
		r = repository.save(r);
		p = fromRemittance.toPayment(r);
		return new ResponseEntity<>(p, httpHeaders(p), CREATED);
	}

	private Long cashBufferDays() {
		return Long.valueOf(gracePeriodCashDeposit);
	}

	private Long checkBufferDays() {
		return Long.valueOf(gracePeriodCheckDeposit);
	}

	private LocalDate endDate(PaymentType p, LocalDate d) {
		LocalDate end = d.minusDays(p == CASH ? cashBufferDays() : checkBufferDays());
		return end;
	}

	private Remittance findOneUndepositedPayment(PaymentType p, String seller, LocalDate d) {
		LocalDate start = LocalDate.parse(goLive);
		LocalDate end = endDate(p, d);
		List<Remittance> l = undepositedPayments(p, start, end);
		if (l == null)
			return null;
		// TODO
		// l.stream().map(Remittance::getDetails).f.filter(i ->
		// s.equals(i.getCustomer().getSeller())).findFirst();
		return null;
	}

	private MultiValueMap<String, String> httpHeaders(Payment p) {
		URI uri = fromCurrentContextPath().path("/{id}").buildAndExpand(p.getId()).toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return httpHeaders;
	}

	private Payment idOnlyPayment(Remittance r) {
		if (r == null)
			return null;
		Payment p = new Payment();
		p.setId(r.getId());
		return p;
	}

	private List<Remittance> undepositedPayments(PaymentType p, LocalDate start, LocalDate end) {
		List<Remittance> l = repository
				.findByDetailsBillingCustomerNotAndReceivedOnNullAndCheckIdNotNullAndPaymentDateBetweenOrderByPaymentDateAsc(
						vendor(), start, end);
		if (p == CASH)
			l = repository
					.findByDetailsBillingCustomerNotAndDepositedOnNullAndCheckIdNullAndPaymentDateBetweenOrderByPaymentDateAsc(
							vendor(), start, end);
		return l;
	}

	private Customer vendor() {
		if (vendor == null)
			vendor = customerRepository.findOne(Long.valueOf(vendorId));
		return vendor;
	}
}