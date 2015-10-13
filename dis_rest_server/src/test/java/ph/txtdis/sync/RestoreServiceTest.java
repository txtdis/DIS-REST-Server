package ph.txtdis.sync;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ph.txtdis.DisServerApp;
import ph.txtdis.exception.FailedReplicationException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DisServerApp.class)
public class RestoreServiceTest {

	static {
		System.setProperty("jasypt.encryptor.password", "I'mAdmin4txtDIS@PostgreSQL");
	}

	@Autowired
	private RestoreService service;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRestore() throws FailedReplicationException {
		service.restore();
	}

}
