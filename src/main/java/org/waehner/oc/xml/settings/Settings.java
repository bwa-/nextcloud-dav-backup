package org.waehner.oc.xml.settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Settings{

	@XmlElement(name="BaseUrl")
	public final Value baseUrl;
	
	@XmlElement(name="CaldavUrl")
	public final Value caldavUrl;
	
	@XmlElement(name="CarddavUrl")
	public final Value carddavUrl;
	
	@XmlElement(name="Outdir")
	public final Value outdir;
	
	private Settings(){
		this.baseUrl = null;
		this.caldavUrl = null;
		this.carddavUrl = null;
		this.outdir = null;
	}

}
