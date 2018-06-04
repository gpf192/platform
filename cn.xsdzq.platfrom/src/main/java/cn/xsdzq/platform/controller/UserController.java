package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.UserDTO;
import cn.xsdzq.platform.model.UserRoleDTO;
import cn.xsdzq.platform.service.IRoleService;
import cn.xsdzq.platform.service.IUserService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.UserUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Autowired
	@Qualifier("roleServiceImpl")
	private IRoleService roleService;

	@RequestMapping(value = "/add", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> add(@Validated @RequestBody UserDTO userDTO) {

		logger.info(userDTO.toString());
		UserEntity userEntity = UserUtil.convertUserEntityByUserDTOADD(userDTO);
		// 加密
		String pw1 = userEntity.getPassword();
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		pw1 = encode.encode(pw1);
		userEntity.setPassword(pw1);
		userService.addUser(userEntity);
		return GsonUtil.buildMap(0, "ok", null);

	}

	@RequestMapping(value = "/modify", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modify(@RequestBody UserDTO userDTO) {

		logger.info(userDTO.toString());
		UserEntity userEntity = UserUtil.convertUserEntityByUserDTO(userDTO);
		// 保持用户原有角色
		userEntity.setRoleEntities(userService.findUserById(userDTO.getId()).getRoleEntities());
		// 加密
		String pw1 = userEntity.getPassword();
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		pw1 = encode.encode(pw1);
		userEntity.setPassword(pw1);
		System.out.println(userEntity.toString());
		userService.modifyUser(userEntity);
		return GsonUtil.buildMap(0, "ok", null);

	}

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody UserDTO userDTO) {

		logger.info(userDTO.toString());
		UserEntity userEntity = UserUtil.convertUserEntityByUserDTO(userDTO);
		userService.deleteUser(userEntity);
		return GsonUtil.buildMap(0, "ok", null);

	}

	@RequestMapping(value = "/getAll", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAll() {

		List<UserDTO> userDTOs = userService.findAll();
		return GsonUtil.buildMap(0, "ok", userDTOs);

	}

	@RequestMapping(value = "/userAddRoles", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addRoles(@RequestBody UserRoleDTO userRoleDTO) {
		logger.info("userId: " + userRoleDTO.getUser_id());
		logger.info("size: " + userRoleDTO.getRoleDTOs().size());
		userService.addRoles(userRoleDTO);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/userModifyRoles", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> ModifyRoles(@RequestBody UserRoleDTO userRoleDTO) {
		logger.info("userId: " + userRoleDTO.getUser_id());
		logger.info("size: " + userRoleDTO.getRoleDTOs().size());
		userService.modifyRoles(userRoleDTO);
		return GsonUtil.buildMap(0, "ok", null);
	}

}
