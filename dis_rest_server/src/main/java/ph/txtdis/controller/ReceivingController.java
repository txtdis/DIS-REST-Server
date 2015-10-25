package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Receiving;
import ph.txtdis.repository.ReceivingRepository;

@RestController("receivingController")
@RequestMapping("/receivings")
public class ReceivingController extends SpunController<ReceivingRepository, Receiving, Long> {

	@RequestMapping(path = "/booking", method = RequestMethod.GET)
	public ResponseEntity<?> findByBooking(@RequestParam("id") Long id) {
		Receiving receiving = repository.findByReferenceId(id);
		return new ResponseEntity<>(receiving, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Receiving> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}