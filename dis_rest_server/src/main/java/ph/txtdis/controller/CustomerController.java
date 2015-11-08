package ph.txtdis.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Customer;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.type.CustomerType;

@RestController("customerController")
@RequestMapping("/customers")
public class CustomerController extends SpunController<CustomerRepository, Customer, Long> {

	@RequestMapping(path = "/banks", method = GET)
	public ResponseEntity<?> findBanks() {
		List<Customer> l = repository.findByDeactivatedOnNullAndTypeOrderByNameAsc(CustomerType.BANK);
		l = l.stream().map(c -> idAndNameOnly(c)).collect(toList());
		return new ResponseEntity<>(l, OK);
	}

	@RequestMapping(path = "/find", method = GET)
	public ResponseEntity<?> findByName(@RequestParam("name") String text) {
		Customer entity = repository.findByName(text);
		return new ResponseEntity<>(entity, OK);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<?> list() {
		Iterable<Customer> list = repository.findAll();
		return new ResponseEntity<>(list, OK);
	}

	private Customer idAndNameOnly(Customer c) {
		Customer n = new Customer();
		n.setId(c.getId());
		n.setName(c.getName());
		return n;
	}
}