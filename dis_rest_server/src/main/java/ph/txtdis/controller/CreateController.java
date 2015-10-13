package ph.txtdis.controller;

import java.io.Serializable;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class CreateController<R extends CrudRepository<T, ID>, T, ID extends Serializable> {

	@Autowired
	R repository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody T body) {
		T entity = repository.save(body);
		return new ResponseEntity<T>(entity, httpHeaders(uri(entity)), HttpStatus.CREATED);
	}

	protected abstract String pathName();

	protected abstract Object pathObject(T entity);

	private <P> URI uri(T entity) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(pathName()).buildAndExpand(pathObject(entity))
				.toUri();
	}

	private MultiValueMap<String, String> httpHeaders(URI uri) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return httpHeaders;
	}
}