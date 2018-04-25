package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.UserRepository;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.RoleDTO;
import cn.xsdzq.platform.model.UserDTO;
import cn.xsdzq.platform.model.UserRoleDTO;
import cn.xsdzq.platform.util.RoleUtil;
import cn.xsdzq.platform.util.UserUtil;

@Service(value = "userServiceImpl")
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findUserById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(id);
	}

	@Override
	public UserEntity findUserByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findUserByName(name);
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		List<UserEntity> userEntities = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (UserEntity userEntity : userEntities) {
			userDTOs.add(UserUtil.convertUserDTOByUserEntity(userEntity));
		}
		return userDTOs;
	}

	@Override
	@Transactional
	public void addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		userRepository.addUser(userEntity);
	}

	@Override
	@Transactional
	public void addRoles(UserRoleDTO userRoleDTO) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findUserById(userRoleDTO.getUser_id());
		Set<RoleEntity> roleEntities = new HashSet<>();
		List<RoleEntity> postRoleEntity = new ArrayList<>();
		for (RoleDTO roleDTO : userRoleDTO.getRoleDTOs()) {
			postRoleEntity.add(RoleUtil.convertRoleEntityByRoleDTO(roleDTO));

		}

		for (RoleEntity roleEntity : postRoleEntity) {

			if (!userEntity.getRoleEntities().contains(roleEntity)) {
				userEntity.getRoleEntities().add(roleEntity);
			}
		}

		userRepository.addRoles(userEntity, userEntity.getRoleEntities());

	}

}
