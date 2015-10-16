package ph.txtdis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.InvoiceBooklet;
import ph.txtdis.repository.InvoiceBookletRepository;

@RestController("invoiceBookletController")
@RequestMapping("/invoiceBooklets")
public class InvoiceBookletController extends IdController<InvoiceBookletRepository, InvoiceBooklet, Long> {

	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@RequestParam("prefix") String prefix, @RequestParam("id") Long id,
			@RequestParam("suffix") String suffix) {
		if (prefix.isEmpty())
			prefix = null;
		if (suffix.isEmpty())
			suffix = null;
		InvoiceBooklet entity = repository.findByPrefixAndSuffixAndStartIdLessThanEqualAndEndIdGreaterThanEqual(prefix,
				suffix, id, id);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<InvoiceBooklet> list = repository.findByOrderByPrefixAscStartIdAscSuffixAsc();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}