package ph.txtdis.sync;

import org.junit.Before;
import org.junit.Test;

import ph.txtdis.sync.BackupUploader;

public class BackupUploaderTest {

	private BackupUploader backupUploader;

	@Before
	public void setUp() throws Exception {
		backupUploader = new BackupUploader();
	}

	@Test
	public void testUpload() throws Exception {
		backupUploader.upload();
	}
}
