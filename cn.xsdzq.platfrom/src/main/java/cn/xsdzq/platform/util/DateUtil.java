package cn.xsdzq.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getStandardDate(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		String standardString = sFormat.format(date);
		return standardString;
	}
	
	public static Date stringToDate(String date) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date1 = null ;
		
			try {
				date1 = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return date1;
	}
	
	public static Date stringToDate1(String date) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date1 = null ;
		
			try {
				date1 = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return date1;
	}
	
	public static Date stringToDateAndSeconds(String date) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date1 = null ;
		
			try {
				date1 = sdf.parse(date +" 23:59:59");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return date1;
	}
	public static String DateToString(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
			
	}
	
	public static String DateToStringAsMonth(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
			
	}
	public static int DateToStringAsDayWithoutLine(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		
		return  Integer.parseInt(sdf.format(date)) ;
			
	}
	public static String Dateymd(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		
		return  sdf.format(date) ;
			
	}
}
