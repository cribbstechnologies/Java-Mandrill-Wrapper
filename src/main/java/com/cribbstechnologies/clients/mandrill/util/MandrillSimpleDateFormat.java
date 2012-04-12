package com.cribbstechnologies.clients.mandrill.util;

import java.text.SimpleDateFormat;

/**
 * Mandrill date objects are in the format of "2012-03-03 17:58:56"
 * 
 * The classes within this client jar will always return dates as Strings. This class can be used to 
 * convert them to/from proper Date objects. 
 * @author Brian Cribbs
 *
 */
public class MandrillSimpleDateFormat extends SimpleDateFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8742099030724331039L;

	public MandrillSimpleDateFormat() {
		super("yyyy-MM-dd kk:mm:ss");
	}
}
