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
		
		else return 16;
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
	public static int getEmpVoteMethodNum (String empName, String voteFromUser) {
		if("".equals(empName) && "".equals(voteFromUser) ) {
			return 1;//全量查找
		}
		if(!"".equals(empName) && !"".equals(voteFromUser) ) {
			return 2;// 查询条件 empName、voteFromUser
		}		
		if(!"".equals(empName) && "".equals(voteFromUser) ) {
			return 3;//查询条件 empName
		}		
		if("".equals(empName) && !"".equals(voteFromUser) ) {
			return 4;//查询条件 voteFromUser
		}

		else return 5;
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
				return 3;//查询条件：username
			}		
			if(!"".equals(username) && !"".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 4;//查询条件：account
			}	
			if(!"".equals(username) && "".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 5;//查询条件：surceId
			}		
			if("".equals(username) && !"".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 6;//查询条件：username \account
			}
			//两个条件
			if(!"".equals(username) && !"".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 7;//查询条件：username \surceId
			}		
			if(!"".equals(username) && "".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 8;//查询条件：account\ surceId
			}
			if(!"".equals(username) && "".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 9;//查询条件：username \surceId
			}		
			if("".equals(username) && !"".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 10;//查询条件：account\ surceId
			}
			if("".equals(username) && !"".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 11;//查询条件：username \surceId
			}		
			if("".equals(username) && "".equals(clientId) && !"".equals(empName) && !"".equals(empCode)) {
				return 12;//查询条件：account\ surceId
			}
			//一个条件
			if(!"".equals(username) && "".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 13;//四个条件一起查询
			}
			if("".equals(username) && !"".equals(clientId) && "".equals(empName) && "".equals(empCode)) {
				return 14;//四个条件一起查询
			}
			if("".equals(username) && "".equals(clientId) && !"".equals(empName) && "".equals(empCode)) {
				return 15;//四个条件一起查询
			}
			if("".equals(username) && "".equals(clientId) && "".equals(empName) && !"".equals(empCode)) {
				return 16;//四个条件一起查询
			}
			
			else return 17;
		}
}
