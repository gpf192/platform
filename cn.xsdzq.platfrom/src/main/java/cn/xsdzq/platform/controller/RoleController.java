package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.model.RoleAuthorityDTO;
import cn.xsdzq.platform.model.RoleDTO;
import cn.xsdzq.platform.model.UserDTO;
import cn.xsdzq.platform.service.IAuthorityService;
import cn.xsdzq.platform.service.IRoleService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.RoleUtil;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	@Qualifier("roleServiceImpl")
	private IRoleService roleService;

	@Autowired
	@Qualifier("authorityServiceImpl")
	private IAuthorityService authorityService;

	@RequestMapping(value = "/add", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addRole(@RequestBody RoleDTO roleDTO) {
		RoleEntity roleEntity = RoleUtil.convertRoleEntityByRoleDTOADD(roleDTO);
		roleService.addRole(roleEntity);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/getExtraRole", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getExtraRole(@RequestBody UserDTO userDTO) {
		List<RoleDTO> roleDTOs = roleService.findExtraRole(userDTO);
		return GsonUtil.buildMap(0, "ok", roleDTOs);
	}

	@RequestMapping(value = "/getAll", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllRole() {
		List<RoleEntity> roleEntities = roleService.findAllRole();
		List<RoleDTO> roleDTOs = new ArrayList<>();
		if (roleEntities != null) {
			for (RoleEntity roleEntity : roleEntities) {
				roleDTOs.add(RoleUtil.convertUserDTOByUserEntity(roleEntity));
			}
		}
		return GsonUtil.buildMap(0, "ok", roleEntities);
	}

	@ResponseBody
	@RequestMapping(value = "/getPermissions", method = GET, produces = "application/json; charset=utf-8")
	public Map<String, Object> getPermissions() {
		RoleEntity roleEntity = roleService.findRoleById(9);
		Set<AuthorityEntity> authorityEntities = roleEntity.getAuthorityEntities();
		return GsonUtil.buildMap(0, "ok", authorityEntities);
	}

	@ResponseBody
	@RequestMapping(value = "/getPermissionsByUser", method = POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getPermissionsByUser(@RequestBody UserDTO userDTO) {
		List<RoleDTO> roleDTOs = roleService.findMyRole(userDTO);
		return GsonUtil.buildMap(0, "ok", roleDTOs);
	}

	@RequestMapping(value = "/addPermissions", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addPermissions(@RequestBody RoleAuthorityDTO roleAuthorityDTO) {

		// AuthorityEntity authorityEntity = authorityService.findAuthorityById(8);
		// RoleEntity roleEntity = roleService.findRoleById(9);
		roleService.appendAuthorities(roleAuthorityDTO);
		return GsonUtil.buildMap(0, "ok", null);
	}

}
