package cn.xsdzq.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ForwardController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView forwardLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			//model.addObject("error", "用户名或密码不正确ww!");
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));

		}else {
			model.addObject("error", "");
			}

		if (logout != null) {
			model.addObject("msg", "您已成功注销系统.");
		}
		model.setViewName("login");
		return model;
	}
	
	//自定义错误类型
    private String getErrorMessage(HttpServletRequest request, String key){
        Exception exception =
                (Exception) request.getSession().getAttribute(key);
        String error = null;
    	//密码错误
        if (exception instanceof BadCredentialsException) {
            error = "用户名或密码错误！";
        }
        if("noUser".equals(exception.getMessage())) {
        	error = "用户名或密码错误！";
        }
        if("locked".equals(exception.getMessage())) {
        	error = "该用户已被锁定，请联系管理员！";
        }
        return error;
    }

}
