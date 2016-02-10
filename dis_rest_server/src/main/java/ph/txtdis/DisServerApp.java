package ph.txtdis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@SpringBootApplication
public class DisServerApp {

	public static void main(String[] args) {
		System.setProperty("jasypt.encryptor.password", "I'mAdmin4txtDIS@PostgreSQL");
		SpringApplication.run(DisServerApp.class, "--debug");
	}
}