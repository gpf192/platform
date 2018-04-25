package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.model.RoleDTO;
import cn.xsdzq.platform.service.IAuthorityService;
import cn.xsdzq.platform.util.AuthorityUtil;
import cn.xsdzq.platform.util.GsonUtil;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

	@Autowired
	private IAuthorityService authorityService;

	@RequestMapping(value = "/add", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addAuthority() {
		AuthorityEntity authorityEntity = new AuthorityEntity();
		authorityEntity.setAuthority("添加用户");
		authorityEntity.setParent_id(7);
		authorityEntity.setLevel(2);
		authorityEntity.setSort(1);
		authorityEntity.setResource_path("http://www.xsdzq.cn/xx.jpg");
		authorityService.addAuthority(authorityEntity);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/getAll", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllAuthority() {
		List<AuthorityEntity> authorityEntities = authorityService.getAllAuthority();
		JSONArray jsonArray = AuthorityUtil.getJsonAuthority(authorityEntities);
		return GsonUtil.buildMap(0, "ok", jsonArray);
	}

	@RequestMapping(value = "/getAllByRole", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllAuthorityByRole(@RequestBody RoleDTO roleDTO) {
		List<AuthorityEntity> authorityEntities = authorityService.getAllByRole(roleDTO);
		JSONArray jsonArray = AuthorityUtil.getJsonAuthority(authorityEntities);
		return GsonUtil.buildMap(0, "ok", jsonArray);
	}

}
