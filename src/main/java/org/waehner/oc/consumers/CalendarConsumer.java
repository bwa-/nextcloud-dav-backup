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
import org.waehner.oc.xml.users.Calendar;

/**
 * Consumes Calendar objects from the configuration file
 * and exports data from the specified calendar to a file.
 * @author bwa-
 */
public class CalendarConsumer implements Consumer<Calendar> {

	private static final Logger logger = LogManager.getLogger();
	private final Settings settings;
	private final String username;
	private final String password;

	/**
	 * Construct a new instance of this class
	 * @param username Name of user that owns the calendar
	 * @param password Password of user
	 * @param settings Settings element (from the configuration file)
	 */
	public CalendarConsumer(String username, String password, Settings settings) {
		this.username = username;
		this.password = password;
		this.settings = settings;
	}

	@Override
	public void accept(Calendar calendar) {
		try (CloseableHttpClient client = NextcloudDavBackup.getHttpClient(username, password)) {
			logger.info(ResourceReader.getString("processingCalendar", calendar.name));
			String url = getCaldavUrl(username, calendar);
			logger.trace(ResourceReader.getString("caldavUrl", url));
			HttpGet get = new HttpGet(url);

			CloseableHttpResponse response = client.execute(get);
			logger.trace(ResourceReader.getString("executedGet"));
			HttpEntity entity = response.getEntity();
			String outfile = settings.outdir + "/calendar_" + username + "_" + calendar.name + "_"
					+ LocalDate.now().format(NextcloudDavBackup.DATE_FORMAT) + ".ics";
			logger.debug(ResourceReader.getString("outfile", outfile));

			FileUtils.copyInputStreamToFile(entity.getContent(), new File(outfile));
			logger.debug(ResourceReader.getString("copySuccess"));
			response.close();
			logger.trace(ResourceReader.getString("responseClosed"));
		} catch (IOException e) {
			logger.error(ResourceReader.getString("calendarError", calendar.name), e);
		}
	}

	private String getCaldavUrl(String username, Calendar calendar) throws UnsupportedEncodingException {
		return settings.caldavUrl.toString().replace("$BASEURL", settings.baseUrl.toString())
				.replace("$USERNAME", username).replace("$CALENDARNAME", URLEncoder.encode(calendar.name, "UTF-8"));
	}

}
