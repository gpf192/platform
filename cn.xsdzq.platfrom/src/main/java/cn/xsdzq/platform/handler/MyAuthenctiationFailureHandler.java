package cn.xsdzq.platform.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.service.IUserService;

@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler  extends SimpleUrlAuthenticationFailureHandler{
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String username = request.getParameter("username").toString();
		System.out.println("进入认证失败处理类***************************************** "+username);
		logger.info("进入认证失败处理类***************************************** "+username);
		exception.getMessage();
		System.out.println("进入认证失败处理类11***************************************** "+exception.getMessage());
//		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		//转发到login
		//查询是否有该用户
		UserEntity userEntity = null;
		try {
			userEntity = userService.findUserByName(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("该用户不存在 "+username);
			System.out.println("该用户不存在*** "+username);
		}
	
		if (userEntity != null) {
			int lockFlag = userEntity.getLockFlag();
			if(lockFlag < 5){
				userEntity.setLockFlag(lockFlag+1);
				userService.modifyUser(userEntity);
			}
			System.out.println("用户姓名是      "+userEntity.getUsername());
		}
		request.getSession().setAttribute("error", exception.getLocalizedMessage());
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
		
		response.sendRedirect("/platform/login?error");
		return;
	}
	
}
