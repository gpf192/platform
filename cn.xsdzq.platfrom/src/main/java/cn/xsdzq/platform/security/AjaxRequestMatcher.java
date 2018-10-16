package cn.xsdzq.platform.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sun.istack.internal.logging.Logger;

public class AjaxRequestMatcher implements RequestMatcher {

	private static Logger logger = Logger.getLogger(AjaxRequestMatcher.class);

	@Override
	public boolean matches(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// return false;
		String ajaxFlag = request.getHeader("X-Requested-With");
		logger.info(ajaxFlag);
		return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
		//return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))
		//		|| request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json");
	}

}
