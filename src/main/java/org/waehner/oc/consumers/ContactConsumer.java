package org.waehner.oc.consumers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waehner.oc.NextcloudDavBackup;
import org.waehner.oc.i18n.ResourceReader;
import org.waehner.oc.xml.settings.Settings;
import org.waehner.oc.xml.users.Contacts;

public class ContactConsumer implements Consumer<Contacts> {

	private static final Logger logger = LogManager.getLogger();
	private final Settings settings;
	private final String username;
	private final String password;
	
	public ContactConsumer(String name, String password, Settings settings) {
		this.username = name;
		this.password = password;
		this.settings = settings;
	}

	@Override
	public void accept(Contacts contacts) {
		logger.info(ResourceReader.getString("processingAddressbook", contacts.name));

		try(CloseableHttpClient client = NextcloudDavBackup.getHttpClient(username, password)){
			String url = getCarddavUrl(contacts);
			logger.trace(ResourceReader.getString("carddavUrl", url));

			HttpGet get = new HttpGet(url);
			
			CloseableHttpResponse response = client.execute(get);
			logger.trace(ResourceReader.getString("executedGet"));
			HttpEntity entity = response.getEntity();
			String outfile = settings.outdir + "/contacts_" + username + "_" + contacts.name + "_"
					+ LocalDate.now().format(NextcloudDavBackup.DATE_FORMAT)
					+ ".vcf";
			logger.debug(ResourceReader.getString("outfile", outfile));

			FileUtils.copyInputStreamToFile(entity.getContent(), new File(outfile));
			logger.debug(ResourceReader.getString("copySuccess"));
			response.close();
			logger.trace(ResourceReader.getString("responseClosed"));
		} catch (IOException e) {
			logger.error(ResourceReader.getString("addressbookError", contacts.name), e);
		}
	}
	
	private String getCarddavUrl(Contacts contacts)
			throws UnsupportedEncodingException {
		return settings.carddavUrl.toString().replace("$BASEURL", settings.baseUrl.toString())
				.replace("$USERNAME", username).replace("$ADDRESSBOOK", URLEncoder.encode(contacts.name, "UTF-8"));
	}
}
