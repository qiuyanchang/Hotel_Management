package com.ischoolbar.programmer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	
	
	
	  public static int daysBetween(Date smdate,Date bdate){
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        try {
				smdate=sdf.parse(sdf.format(smdate));
				bdate=sdf.parse(sdf.format(bdate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
	        
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(smdate);
	        long time1 = cal.getTimeInMillis();
	        cal.setTime(bdate);
	        long time2 = cal.getTimeInMillis();
	        long between_days=(time2-time1)/(1000*3600*24);
	        return Integer.parseInt(String.valueOf(between_days));
	    }
	
	
}
