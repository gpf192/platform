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
		System.out.println("get url: " + url);

		// 先循环配置
		for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {
			if (antPathMatcher.match(entry.getKey(), url)) {
				return SecurityConfig.createList(entry.getValue());
			}
		}

		AuthorityEntity authorityEntity = authorityRepository.findAuthorityByUrl(url.trim());
		if (null == authorityEntity) {
			// 没有匹配到,默认是要登录才能访问
			System.out.println("默认: " + "ROLE_DEFULT");
			return SecurityConfig.createList("ROLE_DEFULT");
		} else {
			System.out.println("权限: " + authorityEntity.getAuthority());
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
