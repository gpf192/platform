package cn.xsdzq.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("say")
	public void sayHello() {
		System.out.println("say hello");
	}

}
