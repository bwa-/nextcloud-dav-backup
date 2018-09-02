package org.waehner.oc.xml.users;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contacts {
	@XmlAttribute(name="name")
	public final String name;
	
	private Contacts(){
		this.name = null;
	}
}
