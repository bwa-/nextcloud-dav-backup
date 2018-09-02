package org.waehner.oc.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.waehner.oc.xml.settings.Settings;
import org.waehner.oc.xml.users.User;

/**
 * Root element of the config file
 * @author bwa-
 */
@XmlRootElement(name="NextcloudDavBackupConf")
public class NextcloudDavBackupConf {

	@XmlElement(name="Settings")
	private final Settings settings;
	
	@XmlElementWrapper(name="Users")
	@XmlElement(name="User")
	private final List<User> users;
	
	/**
	 * @return Settings element of the config file
	 */
	public Settings getSettings() {
		return settings;
	}

	/**
	 * @return Users element of the config file
	 */
	public List<User> getUsers() {
		return users;
	}

	private NextcloudDavBackupConf(){
		this.settings = null;
		this.users = null;
	}
	
	/**
	 * Unmarshals a config file and returns the root element as a pojo
	 * @param configPath Path to the config file
	 * @return the config file as a pojo
	 * @throws JAXBException if unmarshalling fails
	 */
	public static final NextcloudDavBackupConf initialize(String configPath) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(NextcloudDavBackupConf.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		return (NextcloudDavBackupConf)unmarshaller.unmarshal(new File(configPath));
	}
}
