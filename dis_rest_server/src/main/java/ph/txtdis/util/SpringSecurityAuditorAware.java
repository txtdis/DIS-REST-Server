package ph.txtdis.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("springSecurityAuditorAware")
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		if (!Spring.isAuthenticated())
			return "SYSGEN";
		return Spring.username();
	}
}
