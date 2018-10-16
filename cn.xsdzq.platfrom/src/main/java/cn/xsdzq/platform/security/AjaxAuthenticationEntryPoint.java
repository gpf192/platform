package cn.xsdzq.platform.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.sun.istack.internal.logging.Logger;

public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static Logger logger = Logger.getLogger(AjaxAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info(request.getMethod());
		logger.info(request.getHeader("Accept"));
		if (isAjaxRequest(request)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		} else {
			response.sendRedirect("/platform/login");
		}
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		//String ajaxFlag = request.getHeader("X-Requested-With");
		//logger.info("ajaxFlag: "+ajaxFlag);
		String accept = request.getHeader("Accept");
		return accept != null && accept.contains("application/json");
	}

}
