package cn.xsdzq.platform.dao;

import cn.xsdzq.platform.entity.UserAttempts;

public interface  UserDetailsDao {
	
	    void updateFailAttempts(String username);
	    void resetFailAttempts(String username);
	    UserAttempts getUserAttempts(String username);
	
}
