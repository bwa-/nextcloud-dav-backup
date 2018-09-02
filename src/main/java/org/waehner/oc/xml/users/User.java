package org.waehner.oc.xml.users;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	@XmlAttribute(name="name")
	public final String name;
	
	@XmlAttribute(name="password")
	public final String password;
	
	@XmlElement(name="Contacts")
	private final List<Contacts> contacts;
	
	@XmlElement(name="Calendar")
	private final List<Calendar> calendars;
	
	private User(){
		this.name = null;
		this.password = null;
		this.contacts = null;
		this.calendars = null;
	}

	public List<Contacts> getContacts() {
		return contacts;
	}

	public List<Calendar> getCalendars() {
		return calendars;
	}
}
