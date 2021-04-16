package cn.xsdzq.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	public static String getStandardDate(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		String standardString = sFormat.format(date);
		return standardString;
	}
	public static int getNowDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
	    Date pre = c.getTime();
		
	    int sd = Integer.parseInt(sdf.format(pre)) ;
		return sd;
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
	
	public static Date stringToDateAndSeconds1(String date) {
		String d = date.substring(0, 4)+"-"+date.substring(4, 6)
		+"-"+date.substring(6, 8);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date1 = null ;
		
			try {
				date1 = sdf.parse(d +" 23:59:59");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return date1;
	}
	
	public static String stringToDateAndSeconds2(int date) {
		String sdate = String.valueOf(date);
		String d = sdate.substring(0, 4)+"-"+sdate.substring(4, 6)
		+"-"+sdate.substring(6, 8)+" 00:00:00";
		return d;
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
	public static String Dateym(String s) {
		
		return  s.substring(0, 4)+"-"+s.substring(4, 6);
			
	}
	public static int getPreDayAsInt(int num) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -num);
	    Date pre = c.getTime();
		
		return  Integer.parseInt(sdf.format(pre)) ;
			
	}
	
	public static String  getPreDayAsString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
	    Date pre = c.getTime();
		
		return  sdf.format(pre) ;
			
	}
	public static int getFutureDayAsInt(int num) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, +num);
	    Date pre = c.getTime();
		
		return  Integer.parseInt(sdf.format(pre)) ;
			
	}
	public static int getFutureDayAsInt(String day,int num) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date date1 = null ;
		
		try {
			date1 = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.DATE, +num);
		return Integer.parseInt(sdf.format(c.getTime()));
			
	}
	public static void main(String[] args) {
		//StringBuilder s = new StringBuilder();
		//String a  = "33"+s.append(55)+s.append(true)+s.append("66").toString();
		String s = "20213456";
		//System.out.println(s.substring(0, 4)+"-"+s.substring(4, 6)
		//+"-"+s.substring(6, 8));
		System.out.println( stringToDateAndSeconds1("20210326"));
	}	
}
