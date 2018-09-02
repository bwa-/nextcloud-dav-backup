package org.waehner.oc.xml.users;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contacts elements of the config file
 * @author bwa-
 */
@XmlRootElement
public class Contacts {
	
	/**
	 * Name of the addressbook
	 */
	@XmlAttribute(name="name")
	public final String name;
	
	private Contacts(){
		this.name = null;
	}
}
