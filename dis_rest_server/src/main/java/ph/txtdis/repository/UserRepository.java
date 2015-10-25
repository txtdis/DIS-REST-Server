package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.User;
import ph.txtdis.type.UserType;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {

	User findByEmail(@Param("email") String email);

	// @Query("select u from User u join u.roles r where u.enabled = true and
	// r.authority = :role "
	// + "order by u.username asc")
	List<User> findByEnabledTrueAndRolesAuthorityOrderByUsernameAsc(@Param("role") UserType role);

	List<User> findByEnabledTrueOrderByUsernameAsc();
}
