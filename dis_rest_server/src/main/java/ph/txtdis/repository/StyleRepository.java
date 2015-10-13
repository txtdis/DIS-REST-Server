package ph.txtdis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Style;

@Repository("styleRepository")
public interface StyleRepository extends CrudRepository<Style, Long> {

	// @Query("select s from Style s where s.user = :user")
	// Style get(@Param("user") User user);
}
