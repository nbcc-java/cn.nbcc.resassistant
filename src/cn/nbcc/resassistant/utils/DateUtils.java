package cn.nbcc.resassistant.utils;

import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.util.GregorianCalendar;

public class DateUtils {
	public static int getYear(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		return gc.get(Calendar.YEAR);
	}
}

