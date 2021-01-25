package com.scryanalytics.HallBooking.Util;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class HallBookingUtil {

	public static Date convertToSqlDate(java.util.Date date)
	{
		String modifiedDate = new SimpleDateFormat("YYYY-MM-DD").format(date);
		Date sqlDate=java.sql.Date.valueOf(modifiedDate);
		return sqlDate;
	}
	
	public static Timestamp convertSqlDateToTimestamp(Date date,Time startTime)
	{
		long millis = date.getTime() + startTime.getTime();
		return new Timestamp(millis);
	}
	
	public static boolean isXAfterY(Timestamp X, Timestamp Y)
	{
		if(X.after(Y))
			return true;
		else
			return false;
	}
	
}
