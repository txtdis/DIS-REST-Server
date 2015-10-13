package ph.txtdis.sync;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ph.txtdis.DisServerApp;
import ph.txtdis.exception.FailedBackupException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DisServerApp.class)
public class BackupServiceTest {

	static {
		System.setProperty("jasypt.encryptor.password", "I'mAdmin4txtDIS@PostgreSQL");
	}

	@Autowired
	private BackupService service;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetBackupBytes() throws FailedBackupException {
		service.backup();
	}
}