package cn.xsdzq.platform.util;

public class MethodUtil {
	public static int getMethodNum (String beginTime, String endTime) {
		if("".equals(beginTime) && "".equals(endTime)) {
			//全为空
			return 1;//全量查找
		}
		if(!"".equals(beginTime) && !"".equals(endTime)){					
			//都有值
			return 2;//按时间区间查询
		}
		if(!"".equals(beginTime) && "".equals(endTime)){					
			//前者不空，后者空
			return 3;//按时间查询,只大于开始时间
		}
		else return 4;//前者空，后者不空
	}
	public static int getEmpMethodNum (String emp_name, String emp_code, String departmentCode) {
		if("".equals(emp_name) && "".equals(emp_code) && "".equals(departmentCode)) {
			return 1;//全量查找
		}
		if(!"".equals(emp_name) && !"".equals(emp_code) && !"".equals(departmentCode)) {
			return 2;//三个条件一起查询
		}
		if(!"".equals(emp_name) && "".equals(emp_code) && "".equals(departmentCode)) {
			return 3;//查询条件  名字
		}
		if("".equals(emp_name) && !"".equals(emp_code) && "".equals(departmentCode)) {
			return 4;//查询条件  code
		}
		if("".equals(emp_name) && "".equals(emp_code) && !"".equals(departmentCode)) {
			return 5;//查询条件  部门
		}
		
		if(!"".equals(emp_name) && !"".equals(emp_code) && "".equals(departmentCode)) {
			return 6;//查询条件  姓名、code
		}
		if(!"".equals(emp_name) && "".equals(emp_code) && !"".equals(departmentCode)) {
			return 7;//查询条件  姓名、部门
		}
		if("".equals(emp_name) && !"".equals(emp_code) && !"".equals(departmentCode)) {
			return 8;//查询条件  code、部门
		}
		else return 9;
	}
	public static int getPrizeRecordMethodNum (String beginTime, String endTime, String prizeName, String account) {
		if("".equals(beginTime) && "".equals(endTime) && "".equals(prizeName) && "".equals(account)) {
			return 1;//全量查找
		}
		if(!"".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
			return 2;//四个条件一起查询
		}
		if(!"".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
			return 3;//a
		}
		if("".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
			return 4;//b
		}
		if("".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
			return 5;//c
		}
		
		if("".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
			return 6;//d
		}
		//两个条件
		
		if(!"".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
			return 7;//ab
		}
		if(!"".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
			return 8;//ac
		}
		if(!"".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
			return 9;//ad
		}
		if("".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
			return 10;//bc
		}
		if("".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
			return 11;//bd
		}
		if("".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
			return 12;//cd
		}
		//三个条件
		if(!"".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
			return 13;//abc
		}
		if(!"".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
			return 14;//acd
		}
		if("".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
			return 15;//bcd
		}
		
		if(!"".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
			return 16;//abd
		}
		else return 17;
	}
	//用户得票数统计
	public static int getUserVoteMethodNum (String username, String account, String surceId) {
		if("".equals(username) && "".equals(account) && "".equals(surceId) ) {
			return 1;//全量查找
		}
		if(!"".equals(username) && !"".equals(account) && !"".equals(surceId)) {
			return 2;//三个条件一起查询
		}
		if(!"".equals(username) && "".equals(account) && "".equals(surceId)) {
			return 3;//查询条件：username
		}		
		if("".equals(username) && !"".equals(account) && "".equals(surceId)) {
			return 4;//查询条件：account
		}	
		if("".equals(username) && "".equals(account) && !"".equals(surceId)) {
			return 5;//查询条件：surceId
		}		
		if(!"".equals(username) && !"".equals(account) && "".equals(surceId)) {
			return 6;//查询条件：username \account
		}
		if(!"".equals(username) && "".equals(account) && !"".equals(surceId)) {
			return 7;//查询条件：username \surceId
		}		
		if("".equals(username) && !"".equals(account) && !"".equals(surceId)) {
			return 8;//查询条件：account\ surceId
		}

		else return 9;
	}
	//员工得票统计
	public static int getEmpVoteMethodNum (String empName, String empCode, String division) {
		if("".equals(empName) && "".equals(empCode) && "".equals(division)) {
			return 1;//全量查找
		}
		if("".equals(empName) && "".equals(empCode) && "".equals(division)) {
			return 2;// 查询条件 empName、empCode\division
		}	
		//1个条件
		if(!"".equals(empName) && "".equals(empCode) && "".equals(division)) {
			return 3;//查询条件 empName
		}		
		if("".equals(empName) && !"".equals(empCode) && "".equals(division)) {
			return 4;//查询条件 empCode
		}
		if("".equals(empName) && "".equals(empCode) && !"".equals(division)) {
			return 5;// 查询条件 division
		}	
		//2个
		if(!"".equals(empName) && !"".equals(empCode) && "".equals(division)) {
			return 6;//查询条件 empName、empCode
		}		
		if(!"".equals(empName) && "".equals(empCode) && !"".equals(division)) {
			return 7;//查询条件 empName、division
		}
		if("".equals(empName) && !"".equals(empCode) && !"".equals(division)) {
			return 8;//查询条件 empCode、division
		}
		else return 9;
	}
	
	//用户投票数统计
		public static int getUserVoteForMethodNum (String username, String clientId, String empName, String empCode) {
			if("".equals(username) && "".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 1;//全量查找
			}
			if(!"".equals(username) && !"".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 2;//四个条件一起查询
			}
			//三个条件
			if(!"".equals(username) && !"".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 3;//查询条件：username\clientId\empName\
			}		
			if(!"".equals(username) && !"".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 4;//查询条件：username\clientId\\empCode
			}	
			if(!"".equals(username) && "".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 5;//查询条件：username\\empName\empCode
			}		
			if("".equals(username) && !"".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 6;//查询条件：\clientId\empName\empCode
			}
			//两个条件
			if(!"".equals(username) && !"".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 7;//查询条件：username\clientId\\
			}		
			if(!"".equals(username) && "".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 8;//查询条件：username\\empName\
			}
			if(!"".equals(username) && "".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 9;//查询条件：username\\\empCode
			}		
			if("".equals(username) && !"".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 10;//查询条件：\clientId\empName\
			}
			if("".equals(username) && !"".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 11;//查询条件：\clientId\\empCode
			}		
			if("".equals(username) && "".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 12;//查询条件：\\empName\empCode
			}
			//一个条件
			if(!"".equals(username) && "".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 13;//查询条件：username\\\
			}
			if("".equals(username) && !"".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 14;//查询条件：\clientId\\
			}
			if("".equals(username) && "".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 15;//查询条件：\\empName\
			}
			if("".equals(username) && "".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 16;//查询条件：\\\empCode
			}
			
			else return 17;
		}
		//获取产品销售数据
		public static int getProductSellMethodNum (String clientId, String code, String zj) {
			if("".equals(clientId) && "".equals(code) && "".equals(zj) ) {
				return 1;//全量查找
			}
			if(!"".equals(clientId) && !"".equals(code) && !"".equals(zj) ) {
				return 2;//三个条件一起查询
			}
			//2个条件
			
			if(!"".equals(clientId) && !"".equals(code) && "".equals(zj) ) {
				return 3;//查询条件：clientId\code
			}	
			if(!"".equals(clientId) && "".equals(code) && !"".equals(zj) ) {
				return 4;//查询条件：clientId\\zj
			}		
			if("".equals(clientId) && !"".equals(code) && !"".equals(zj) ) {
				return 5;//查询条件：\code\zj
			}
			
			//一个条件
			if(!"".equals(clientId) && "".equals(code) && "".equals(zj) ) {
				return 6;//查询条件：clientId\\\
			}
			if("".equals(clientId) && !"".equals(code) && "".equals(zj)) {
				return 7;//查询条件：\code\\
			}
			if("".equals(clientId) && "".equals(code) && !"".equals(zj) ) {
				return 8;//查询条件：\\zj\
			}
			
			
			else return 9;
		}
		//开门红
		
		public static int getAwardResultMethodNum (String beginTime, String endTime, String prizeName, String account) {
			if("".equals(beginTime) && "".equals(endTime) && "".equals(prizeName) && "".equals(account)) {
				return 1;//全量查找
			}
			if(!"".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
				return 2;//四个条件一起查询
			}
			if(!"".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
				return 3;//查询条件  大于开始时间
			}
			if("".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
				return 4;//查询条件  小于结束时间
			}
			if("".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
				return 5;//查询条件  奖品名称
			}
			
			if("".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
				return 6;//查询条件  资金账号
			}
			//两个条件
			
			if(!"".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& "".equals(account)) {
				return 7;//查询条件  大于开始时间、小于结束时间
			}
			if(!"".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
				return 8;//查询条件  大于开始时间、奖品名称
			}
			if(!"".equals(beginTime) && "".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
				return 9;//查询条件  大于开始时间、资金账号
			}
			if("".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
				return 10;//查询条件  小于结束时间、奖品名称
			}
			if("".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
				return 11;//查询条件    小于结束时间、资金账号
			}
			if("".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
				return 12;//查询条件 奖品名称、资金账号
			}
			//三个条件
			if(!"".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& "".equals(account)) {
				return 13;//查询条件 开始时间、 结束时间、 奖品名称
			}
			if(!"".equals(beginTime) && "".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
				return 14;//查询条件  开始时间、 奖品名称、资金账号
			}
			if("".equals(beginTime) && !"".equals(endTime) && !"".equals(prizeName)&& !"".equals(account)) {
				return 15;//查询条件 结束时间、 奖品名称、资金账号
			}
			if(!"".equals(beginTime) && !"".equals(endTime) && "".equals(prizeName)&& !"".equals(account)) {
				return 16;//查询条件 结束时间、 奖品名称、资金账号
			}
			else return 17;
		} 
		//同花顺   , username,orderID  tgName, productName
		public static int getUserOrderForMethodNum (String username, String orderId, String tgName, String productName) {
			if("".equals(username) && "".equals(orderId) && "".equals(tgName) && "".equals(productName)) {
				return 1;//全量查找
			}
			if(!"".equals(username) && !"".equals(orderId) && !"".equals(tgName) && !"".equals(productName)) {
				return 2;//四个条件一起查询
			}
			//三个条件
			if(!"".equals(username) && !"".equals(orderId) && !"".equals(tgName) && "".equals(productName)) {
				return 3;//查询条件：username\orderId\tgName\
			}		
			if(!"".equals(username) && !"".equals(orderId) && "".equals(tgName) && !"".equals(productName)) {
				return 4;//查询条件：username\orderId\\productName
			}	
			if(!"".equals(username) && "".equals(orderId) && !"".equals(tgName) && !"".equals(productName)) {
				return 5;//查询条件：username\\tgName\productName
			}		
			if("".equals(username) && !"".equals(orderId) && !"".equals(tgName) && !"".equals(productName)) {
				return 6;//查询条件：\orderId\tgName\productName
			}
			//两个条件
			if(!"".equals(username) && !"".equals(orderId) && "".equals(tgName) && "".equals(productName)) {
				return 7;//查询条件：username\orderId\\
			}		
			if(!"".equals(username) && "".equals(orderId) && !"".equals(tgName) && "".equals(productName)) {
				return 8;//查询条件：username\\tgName\
			}
			if(!"".equals(username) && "".equals(orderId) && "".equals(tgName) && !"".equals(productName)) {
				return 9;//查询条件：username\\\productName
			}		
			if("".equals(username) && !"".equals(orderId) && !"".equals(tgName) && "".equals(productName)) {
				return 10;//查询条件：\orderId\tgName\
			}
			if("".equals(username) && !"".equals(orderId) && "".equals(tgName) && !"".equals(productName)) {
				return 11;//查询条件：\orderId\\productName
			}		
			if("".equals(username) && "".equals(orderId) && !"".equals(tgName) && !"".equals(productName)) {
				return 12;//查询条件：\\tgName\productName
			}
			//一个条件
			if(!"".equals(username) && "".equals(orderId) && "".equals(tgName) && "".equals(productName)) {
				return 13;//查询条件：username\\\
			}
			if("".equals(username) && !"".equals(orderId) && "".equals(tgName) && "".equals(productName)) {
				return 14;//查询条件：\orderId\\
			}
			if("".equals(username) && "".equals(orderId) && !"".equals(tgName) && "".equals(productName)) {
				return 15;//查询条件：\\tgName\
			}
			if("".equals(username) && "".equals(orderId) && "".equals(tgName) && !"".equals(productName)) {
				return 16;//查询条件：\\\productName
			}
			
			else return 17;
		}
}
