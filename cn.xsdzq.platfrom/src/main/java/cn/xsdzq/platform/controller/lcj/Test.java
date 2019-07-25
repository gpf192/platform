package cn.xsdzq.platform.controller.lcj;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
	/*	Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
        
        SimpleDateFormat sp=new SimpleDateFormat("yyyyMMdd");
        String z=sp.format(d);//获取昨天日期
        
        System.out.println(z);*/
		
		BigDecimal a = new BigDecimal(354.0000000000006);
		BigDecimal b = BigDecimal.valueOf(0.1);
		double d = a.multiply(b).doubleValue();
		Math.round(d) ;
		 System.out.println(Math.round(d));
		 
		 double kk = Double.parseDouble("1.2365");
		 System.out.println(kk);
		/* BigDecimal bd= new BigDecimal(d);
		 Double tt = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 System.out.println("方法一: " + tt);*/
	}
}
