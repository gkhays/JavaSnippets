package org.gkh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
	
	public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	/**
	 * ISO 8601/RFC 3339 represents timezone information a little bit
	 * differently than what is expected by {@link #SimpleDateFormat}. For
	 * example the Google Directory REST API returns timestamps in the form of:
	 * {@code 2015-04-30T15:48:34.000Z}. Convert these to a UNIX timestamp
	 * (number of seconds from the epoch). Where the Unix Epoch started January
	 * 1st, 1970 at UTC.
	 * 
	 * @see <a href="http://www.unixtimestamp.com/">Epoch Unix Time Stamp
	 *      Converter</a>
	 * @see <a
	 *      href="http://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format">How
	 *      to get current moment in ISO 8601 format?</a>
	 * @see <a
	 *      href="http://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date">Converting
	 *      ISO 8601-compliant String to java.util.Date</a>
	 * 
	 * @param timeStamp
	 *            {@link #String} in the format of
	 *            {@code 2015-04-30T15:48:34.000Z}
	 * @return the number of seconds from the epoch
	 */
	public static long iso8601StringToUnixTime(String timeStamp) {
		long unixTime = -1;
		TimeZone tzUTC = Calendar.getInstance().getTimeZone();
		DateFormat format = new SimpleDateFormat(ISO8601_FORMAT);
		format.setTimeZone(tzUTC);
		try {
			// Reminder: getTime returns the number of milliseconds from the
			// Unix epoch.
			unixTime = format.parse(timeStamp).getTime() / 1000;
		} catch (ParseException e) {
			// Return initialized value of -1;
		}
		return unixTime;
	}

	public static String dateToISO8601String() {
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat(ISO8601_FORMAT, Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(now);
	}
	
	// Other mentions:
	// ISO 8601 date parsing utility
	// http://www.java2s.com/Code/Java/Data-Type/ISO8601dateparsingutility.htm

	// org.joda.time.format 
	// Class ISODateTimeFormat
	// http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISODateTimeFormat.html

	// ISO 8601
	// https://en.wikipedia.org/wiki/ISO_8601

	// Date and Time Formats
	// http://www.w3.org/TR/NOTE-datetime

	//https://gist.github.com/kristopherjohnson/6124652
	
	private TimeUtils() {}
}
