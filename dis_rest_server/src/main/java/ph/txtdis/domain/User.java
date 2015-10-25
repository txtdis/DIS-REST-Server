package ph.txtdis.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.txtdis.type.UserType;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable, Keyed<String> {

	private static final long serialVersionUID = -2632553934643767369L;

	@Id
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean enabled;

	@JoinColumn(name = "username")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Authority> roles;

	@OneToOne(cascade = CascadeType.ALL)
	private Style style;

	private String email;

	public User(String username, String password, List<Authority> roles) {
		this.username = username;
		this.password = password;
		this.enabled = true;
		this.roles = roles == null ? Arrays.asList(new Authority(UserType.SELLER)) : roles;
	}

	public User disable() {
		enabled = false;
		return this;
	}

	@Override
	public String getId() {
		return username;
	}

	@Override
	public String toString() {
		return username;
	}
}
