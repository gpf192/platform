package cn.xsdzq.platform.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserManageUtil {

	// Logger logger = LogManager.getLogger(UserManageUtil.class.getName());

	public static User getUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth == null) {
			return null;
		}
		System.out.println("auth.getPrincipal" + auth.getPrincipal().toString());
		User user = (User) auth.getPrincipal();
		System.out.println(user);
		return user;
	}

	public static String getUserName() {

		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth == null) {
			return null;
		}
		Object principal = auth.getPrincipal();
		String name;
		if (principal instanceof User) {
			name = ((User) principal).getUsername();
		} else {
			name = principal.toString();
		}
		return name;
	}

}
