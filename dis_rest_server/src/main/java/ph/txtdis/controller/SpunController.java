package ph.txtdis.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ph.txtdis.domain.Keyed;
import ph.txtdis.repository.SpunRepository;

public abstract class SpunController<R extends SpunRepository<T, ID>, T extends Keyed<ID>, ID extends Serializable>
		extends IdController<R, T, ID>
{
	@RequestMapping(path = "/last", method = RequestMethod.GET)
	public ResponseEntity<?> last() {
		T entity = repository.findFirstByOrderByIdDesc();
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}

	@RequestMapping(path = "/first", method = RequestMethod.GET)
	public ResponseEntity<?> first() {
		T entity = repository.findFirstByOrderByIdAsc();
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}

	@RequestMapping(path = "/next", method = RequestMethod.GET)
	public ResponseEntity<?> next(@RequestParam("id") ID id) {
		T entity = repository.findFirstByIdGreaterThanOrderByIdAsc(id);
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}

	@RequestMapping(path = "/previous", method = RequestMethod.GET)
	public ResponseEntity<?> previous(@RequestParam("id") ID id) {
		T entity = repository.findFirstByIdLessThanOrderByIdDesc(id);
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}
}