package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.istack.internal.logging.Logger;

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
	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@Autowired
	@Qualifier("roleServiceImpl")
	private IRoleService roleService;

	@RequestMapping(value = "/add", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategory(@RequestBody UserDTO userDTO) {

		logger.info(userDTO.toString());
		/*
		 * UserEntity userEntity = new UserEntity(); userEntity.setUsername("系统录入员");
		 * userEntity.setPassword("123456"); userEntity.setPhone("13555555555");
		 * userEntity.setEnabled(true);
		 */
		UserEntity userEntity = UserUtil.convertUserEntityByUserDTOADD(userDTO);
		userService.addUser(userEntity);
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

}
