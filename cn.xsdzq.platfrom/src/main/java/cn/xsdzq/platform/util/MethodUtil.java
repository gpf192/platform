package cn.xsdzq.platform.util;

public class MethodUtil {
	public static int getMethodNum (String beginTime, String endTime) {
		if("".equals(beginTime) && "".equals(endTime)) {
			//时间为空
			return 1;//全量查找
		}
		if(!"".equals(beginTime) && !"".equals(endTime)){					
			//时间不为空
			return 2;//按时间区间查询
		}
		if(!"".equals(beginTime) && "".equals(endTime)){					
			//时间不为空
			return 3;//按时间查询,只大于开始时间
		}
		else return 4;//按时间查询,只小于结束时间
	}
}
