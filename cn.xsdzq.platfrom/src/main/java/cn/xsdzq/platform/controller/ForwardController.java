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
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "用户名或密码不正确!");
			//model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));

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
        String error;
        if (exception instanceof BadCredentialsException) {
            error = "不正确的用户名或密码";
        }else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }else{
            error = "不正确的用户名或密码";
        }
        return error;
    }

}
