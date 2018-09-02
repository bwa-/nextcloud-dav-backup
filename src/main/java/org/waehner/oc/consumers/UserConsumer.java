package org.waehner.oc.consumers;

import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waehner.oc.i18n.ResourceReader;
import org.waehner.oc.xml.settings.Settings;
import org.waehner.oc.xml.users.User;

/**
 * Consumes User objects from the configuration file
 * and exports calendar and contacts data from the specified element to a file.
 * @author bwa-
 */
public class UserConsumer implements Consumer<User> {
	
	private static final Logger logger = LogManager.getLogger();
	private final Settings settings;
	
	/**
	 * Construct a new instance of this class
	 * @param settings Settings element (from the configuration file)
	 */
	public UserConsumer(Settings settings) {
		this.settings = settings;
	}
	
	@Override
	public void accept(User user) {
		logger.info(ResourceReader.getString("processingUser", user.name));

		user.getContacts().forEach(new ContactConsumer(user.name, user.password, settings));
		user.getCalendars().forEach(new CalendarConsumer(user.name, user.password, settings));
		
		logger.trace(ResourceReader.getString("closedClient"));
	}
}
