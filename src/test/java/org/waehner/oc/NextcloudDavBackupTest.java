package org.waehner.oc;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

/**
 * Run the export for tests
 * @author bwa-
 */
public class NextcloudDavBackupTest {

	/**
	 * Run the export for tests
	 * @throws ClientProtocolException
	 * @throws JAXBException
	 * @throws IOException
	 */
	@Test
	public void testMain() throws ClientProtocolException, JAXBException, IOException {
		Locale.setDefault(new Locale("en"));
		NextcloudDavBackup.main(
				new String[] { Paths.get("src", "test", "resources", "testconf.xml").toAbsolutePath().toString() });
	}

}
