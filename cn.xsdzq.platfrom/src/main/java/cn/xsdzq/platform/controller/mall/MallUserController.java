package cn.xsdzq.platform.controller.mall;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.GsonUtil;



@RestController
@RequestMapping(value = "/mall/user")
public class MallUserController {

	@Autowired
	@Qualifier("mallUserServiceImpl")
	private MallUserService mallUserService;

	@PostMapping(value = "/add")
	public Map<String, Object> addMallUser() {

//mallUserService.addMallUser(mallUserEntity);
		return GsonUtil.buildMap(0, "success", null);

	}

}
