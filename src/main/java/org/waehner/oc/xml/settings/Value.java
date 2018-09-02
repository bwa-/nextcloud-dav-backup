package org.waehner.oc.xml.settings;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Value {

	@XmlAttribute
	private final String value;
	
	private Value(){
		this.value = null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
