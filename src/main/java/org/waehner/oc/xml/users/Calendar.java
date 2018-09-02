package org.waehner.oc.xml.users;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Calendar {
	@XmlAttribute(name="name")
	public final String name;
	
	private Calendar(){
		this.name = null;
	}
}
