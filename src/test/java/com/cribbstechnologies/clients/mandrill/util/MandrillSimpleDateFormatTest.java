package com.cribbstechnologies.clients.mandrill.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class MandrillSimpleDateFormatTest {
	
	MandrillSimpleDateFormat format = new MandrillSimpleDateFormat();
	
	@Test
	public void testParse() {
		try {
			Date myDate = format.parse("2012-07-03 17:58:56");
			Calendar cal = Calendar.getInstance();
			cal.setTime(myDate);
			assertEquals(new Integer("2012"), (Integer)cal.get(Calendar.YEAR));
			assertEquals(new Integer("06"), (Integer)cal.get(Calendar.MONTH));
			assertEquals(new Integer("03"), (Integer)cal.get(Calendar.DAY_OF_MONTH));
			assertEquals(new Integer("17"), (Integer)cal.get(Calendar.HOUR_OF_DAY));
			assertEquals(new Integer("58"), (Integer)cal.get(Calendar.MINUTE));
			assertEquals(new Integer("56"), (Integer)cal.get(Calendar.SECOND));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFormat() {
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 9, 1, 14, 30, 24);
		Date toFormat = cal.getTime();
		assertEquals("2011-10-01 14:30:24", format.format(toFormat));
	}

}
