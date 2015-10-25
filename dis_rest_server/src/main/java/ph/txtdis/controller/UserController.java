package ph.txtdis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ph.txtdis.domain.User;
import ph.txtdis.repository.UserRepository;
import ph.txtdis.type.UserType;

@RestController("userController")
@RequestMapping("/users")
public class UserController extends IdController<UserRepository, User, String> {

	@RequestMapping(path = "/email", method = RequestMethod.GET)
	public ResponseEntity<?> findByEmail(@RequestParam("address") String email) {
		User user = repository.findByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<User> users = repository.findByEnabledTrueOrderByUsernameAsc();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(path = "/role", method = RequestMethod.GET)
	public ResponseEntity<?> listByRole(@RequestParam("name") UserType role) {
		List<User> users = repository.findByEnabledTrueAndRolesAuthorityOrderByUsernameAsc(role);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}