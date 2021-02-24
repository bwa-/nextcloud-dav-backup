package org.waehner.oc.xml.users;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

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
