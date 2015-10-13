package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Picking;
import ph.txtdis.repository.PickingRepository;

@RestController("PickingController")
@RequestMapping("/pickings")
public class PickingController extends SpunController<PickingRepository, Picking, Long> {

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/booking", method = RequestMethod.GET)
	public ResponseEntity<?> findByBooking(@RequestParam("id") Long id) {
		Picking pick = repository.findByBookingsId(id);
		return new ResponseEntity<>(pick, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Picking> picks = repository.findAll();
		return new ResponseEntity<>(picks, HttpStatus.OK);
	}
}