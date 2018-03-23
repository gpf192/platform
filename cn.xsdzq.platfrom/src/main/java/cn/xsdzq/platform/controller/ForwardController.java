package cn.xsdzq.platform.controller;

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
		}

		if (logout != null) {
			model.addObject("msg", "您已成功注销系统.");
		}
		model.setViewName("login");

		return model;
	}

}
