package ph.txtdis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.Item;
import ph.txtdis.repository.ItemRepository;

@RestController("itemController")
@RequestMapping("/items")
public class ItemController extends SpunController<ItemRepository, Item, Long> {

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		Iterable<Item> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}