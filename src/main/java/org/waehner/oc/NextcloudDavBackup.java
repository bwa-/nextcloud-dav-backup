package org.waehner.oc;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waehner.oc.consumers.UserConsumer;
import org.waehner.oc.i18n.ResourceReader;
import org.waehner.oc.xml.NextcloudDavBackupConf;

/**
 * Runs backups of the calendars/contacts specified
 * in the config file as caldav/carddav format files.
 * @author bwa-
 */
public class NextcloudDavBackup {
	/**
	 * Date format for log messages and the exported files.
	 */
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

	private static final Logger LOGGER = LogManager.getLogger(NextcloudDavBackup.class);

	/**
	 * Run the backup
	 * @param args First and only expected argument is the path to the config file to use.
	 * @throws JAXBException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, ClientProtocolException, IOException {
		
		if(ArrayUtils.isEmpty(args)) {
			LOGGER.info("Usage: java -jar NextcloudDavBackup.jar conffile.xml");
			return;
		}
		
		LOGGER.info(ResourceReader.getString("start", LocalDate.now().format(DATE_FORMAT)));
		NextcloudDavBackupConf conf = NextcloudDavBackupConf.initialize(args[0]);

		LOGGER.info(ResourceReader.getString("initConfig"));

		conf.getUsers().forEach(new UserConsumer(conf.getSettings()));
	}
	
	/**
	 * @param username Nextcloud user name
	 * @param password Nextcloud password for that user
	 * @return A HttpClient object to use for the backup
	 */
	public static CloseableHttpClient getHttpClient(String username, String password) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		return HttpClientBuilder.create().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.setDefaultCredentialsProvider(credentialsProvider).build();
	}
}
