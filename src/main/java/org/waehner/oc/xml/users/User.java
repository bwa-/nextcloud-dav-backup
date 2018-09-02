package org.waehner.oc.xml.users;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User element of the config file
 * @author bwa-
 */
@XmlRootElement
public class User {

	/**
	 * Name of the user
	 */
	@XmlAttribute(name="name")
	public final String name;
	
	/**
	 * Password of the user
	 */
	@XmlAttribute(name="password")
	public final String password;
	
	/**
	 * Contacts of the user
	 */
	@XmlElement(name="Contacts")
	private final List<Contacts> contacts;
	
	/**
	 * Calendars of the user
	 */
	@XmlElement(name="Calendar")
	private final List<Calendar> calendars;
	
	private User(){
		this.name = null;
		this.password = null;
		this.contacts = null;
		this.calendars = null;
	}

	/**
	 * @return Contacts of the user
	 */
	public List<Contacts> getContacts() {
		return contacts;
	}

	/**
	 * @return Calendars of the user
	 */
	public List<Calendar> getCalendars() {
		return calendars;
	}
}
