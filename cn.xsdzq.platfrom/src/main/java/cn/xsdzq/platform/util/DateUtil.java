package cn.xsdzq.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.xsdzq.platform.constants.CreditRecordConst;

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
		//System.out.println(getFutureDayAsInt("20201026" ,0));
		System.out.println( DateUtil.Dateymd(new Date()));
		System.out.println( getPreDayAsInt(1));
	}	
}
