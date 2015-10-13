package ph.txtdis.controller;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.txtdis.domain.Keyed;

public abstract class IdController<R extends CrudRepository<T, ID>, T extends Keyed<ID>, ID extends Serializable>
		extends CreateController<R, T, ID>
{

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable ID id) {
		T entity = repository.findOne(id);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@Override
	protected String pathName() {
		return "/{id}";
	}

	@Override
	protected Object pathObject(T entity) {
		return entity.getId();
	}
}