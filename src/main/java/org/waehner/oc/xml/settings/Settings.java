package org.waehner.oc.xml.settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Settings element of the config file
 * @author bwa-
 */
@XmlRootElement
public class Settings{

	/**
	 * Base url of the Nextcloud server 
	 */
	@XmlElement(name="BaseUrl")
	public final Value baseUrl;
	
	/**
	 * Current url to export calendars. Might change between versions of Nextcloud.
	 */
	@XmlElement(name="CaldavUrl")
	public final Value caldavUrl;
	
	/**
	 * Current url to export contacts. Might change between versions of Nextcloud.
	 */
	@XmlElement(name="CarddavUrl")
	public final Value carddavUrl;
	
	/**
	 * Directory to write outfiles to.
	 */
	@XmlElement(name="Outdir")
	public final Value outdir;
	
	private Settings(){
		this.baseUrl = null;
		this.caldavUrl = null;
		this.carddavUrl = null;
		this.outdir = null;
	}

}
