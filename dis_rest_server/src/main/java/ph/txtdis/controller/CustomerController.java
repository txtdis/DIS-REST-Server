package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Customer;
import ph.txtdis.repository.CustomerRepository;

@RestController("customerController")
@RequestMapping("/customers")
public class CustomerController extends SpunController<CustomerRepository, Customer, Long> {

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public ResponseEntity<?> findByBooking(@RequestParam("name") String text) {
		Customer entity = repository.findByName(text);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Customer> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}