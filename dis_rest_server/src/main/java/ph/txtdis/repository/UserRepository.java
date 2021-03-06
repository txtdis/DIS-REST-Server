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

	List<User> findByEnabledTrueAndRolesAuthorityInOrderByUsernameAsc(@Param("roles") List<UserType> roles);

	List<User> findByEnabledTrueOrderByUsernameAsc();
}
