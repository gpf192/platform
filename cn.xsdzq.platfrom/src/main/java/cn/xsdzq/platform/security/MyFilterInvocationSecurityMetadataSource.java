package cn.xsdzq.platform.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import cn.xsdzq.platform.dao.AuthorityRepository;
import cn.xsdzq.platform.entity.AuthorityEntity;

public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	private final Map<String, String> urlRoleMap = new HashMap<String, String>() {
		{
			put("/static/**", "ROLE_MAIN_AUTHORITY");
			// put("/front/**", "ROLE_MAIN_AUTHORITY");
			// put("/front/**", "ROLE_ANONYMOUS");
			put("/login", "ROLE_ANONYMOUS");
			put("/loginin/**", "ROLE_ANONYMOUS");
		}
	};

	private AuthorityRepository authorityRepository;

	public MyFilterInvocationSecurityMetadataSource() {

	}

	public MyFilterInvocationSecurityMetadataSource(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String url = filterInvocation.getRequestUrl();
		// 处理get查询字符串
		if (url.indexOf("?") > 0) {
			url = url.split("\\?")[0];
		}
		// 先循环配置
		for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {
			if (antPathMatcher.match("/front/**", url)) {
				String[] roles = { "ROLE_MAIN_AUTHORITY", "ROLE_ANONYMOUS" };
				return SecurityConfig.createList(roles);
			}
			if (antPathMatcher.match(entry.getKey(), url)) {
				return SecurityConfig.createList(entry.getValue());
			}
		}
		// 根据url查询数据库对应的角色

		AuthorityEntity authorityEntity = authorityRepository.findAuthorityByUrl(url.trim());
		if (null == authorityEntity) {
			// 没有匹配到,默认是要登录才能访问
			return SecurityConfig.createList("ROLE_DEFULT");
		} else {
			return SecurityConfig.createList(authorityEntity.getAuthority());
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
