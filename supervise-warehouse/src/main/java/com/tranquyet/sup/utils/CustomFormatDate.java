package com.tranquyet.sup.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomFormatDate {
	public static final String FORMAT_DD_MM_YYYY_HHmmss = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

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

	public static LocalDateTime convertStringToLocalDateTime(String date) {
		LocalDateTime aLDT = LocalDateTime.parse(date);
		return aLDT;
	}

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		return ldt;
	}

	public static String formatLocalDateTime(LocalDateTime time, String dateFormat) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return time.format(formatter);
	}
}
