package ph.txtdis.sync;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class BackupDownloaderTest {

	private BackupDownloader backup;

	@Before
	public void setUp() throws Exception {
		backup = new BackupDownloader();
	}

	@Test
	public void testDownload_whenValid() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(2015, 9, 12, 1, 0, 0);
		backup.download(c.getTime());
	}
}
