package org.waehner.oc.xml.users;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Calendar element of config file
 * @author bwa-
 */
@XmlRootElement
public class Calendar {
	
	/**
	 * Name of the calendar
	 */
	@XmlAttribute(name="name")
	public final String name;
	
	private Calendar(){
		this.name = null;
	}
}
