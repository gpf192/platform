package cn.xsdzq.platform.util;

import java.util.regex.Pattern;

public class PublicUtil {
	

	  public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
	  
	  public static int stringToInt(String str) {  
	         
	        return Integer.parseInt(str) ;
	  }
	  
	public static void main(String[] args) {
		//System.out.println(!isInteger("jj"));
		System.out.println(stringToInt("20200105") < stringToInt("20200106"));
	}
}
