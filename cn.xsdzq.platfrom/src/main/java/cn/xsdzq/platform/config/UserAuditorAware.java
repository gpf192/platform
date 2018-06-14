package cn.xsdzq.platform.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.User;

import cn.xsdzq.platform.util.UserManageUtil;

public class UserAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		User user = UserManageUtil.getUser();
		String name = "default";
		if (user != null) {
			name = user.getUsername();
		}
		return Optional.ofNullable(name);

	}

}
