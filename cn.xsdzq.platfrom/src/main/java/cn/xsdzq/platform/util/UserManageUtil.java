package cn.xsdzq.platform.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserManageUtil {

	public static User getUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth == null) {
			return null;
		}
		User user = (User) auth.getPrincipal();
		return user;
	}

}
