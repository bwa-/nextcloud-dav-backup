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

@XmlRootElement(name="NextcloudDavBackupConf")
public class NextcloudDavBackupConf {

	@XmlElement(name="Settings")
	private final Settings settings;
	
	@XmlElementWrapper(name="Users")
	@XmlElement(name="User")
	private final List<User> users;
	
	public Settings getSettings() {
		return settings;
	}

	public List<User> getUsers() {
		return users;
	}

	private NextcloudDavBackupConf(){
		this.settings = null;
		this.users = null;
	}
	
	public static final NextcloudDavBackupConf initialize(String configPath) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(NextcloudDavBackupConf.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		return (NextcloudDavBackupConf)unmarshaller.unmarshal(new File(configPath));
	}
}
