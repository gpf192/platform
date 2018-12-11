package cn.xsdzq.platform.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.service.IUserService;

@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	/*@Autowired
	private ObjectMapper objectMapper;*/
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//logger.info("登录成功");
		String username = request.getParameter("username").toString();
		String contextPath = request.getContextPath();
		//登陆成功，将锁定标识
		UserEntity userEntity = userService.findUserByName(username);
		int lockFlag = userEntity.getLockFlag();
		if(lockFlag >0){
			userEntity.setLockFlag(0);
			userService.modifyUser(userEntity);
		}
		response.setContentType("application/json;charset=UTF-8");
		response.sendRedirect(contextPath+"/static/index.html");		
	}

}
