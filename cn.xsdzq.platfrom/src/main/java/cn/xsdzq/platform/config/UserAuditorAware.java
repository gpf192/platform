package cn.xsdzq.platform.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import cn.xsdzq.platform.util.UserManageUtil;

public class UserAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		String name = UserManageUtil.getUserName();
		return Optional.ofNullable(name);
	}

}
