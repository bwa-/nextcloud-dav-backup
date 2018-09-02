package org.waehner.oc.xml.settings;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAXB element to get value of settings elements
 * @author bwa-
 */
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
