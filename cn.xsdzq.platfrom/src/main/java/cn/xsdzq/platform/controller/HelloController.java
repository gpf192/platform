package cn.xsdzq.platform.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xsdzq.platform.entity.HelloEntity;
import cn.xsdzq.platform.service.IHelloService;

@Controller
@RequestMapping("/hello")
public class HelloController {

	private IHelloService helloService;

	private static Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	public HelloController(IHelloService helloServiceImpl) {
		this.helloService = helloServiceImpl;
	}

	@RequestMapping(value = "/say", method = RequestMethod.GET)
	public String sayHello() {
		System.out.println("say hello");
		logger.info("say hello");
		HelloEntity helloEntity = new HelloEntity();
		helloEntity.setName("aaa");
		// helloService.addHello(helloEntity);
		return "NewFile";
	}

}
