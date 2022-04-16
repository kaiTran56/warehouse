package com.tranquyet.sup.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CustomFormatDate {
	public static final String FORMAT_DD_MM_YYYY_HHmmss = "dd/MM/yyyy HH:mm:ss";

	public static LocalDateTime convertStringToDate(String dateStr, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Date date = format.parse(dateStr);
			return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
