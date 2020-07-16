package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.UserDTO;

public class UserUtil {

	public static UserEntity convertUserEntityByUserDTO(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setAlias(userDTO.getAlias());
		userEntity.setLevel(userDTO.getLevel());
		userEntity.setLockFlag(userDTO.getLockFlag());
		userEntity.setDepartment(userDTO.getDepartment());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setEnabled(userDTO.isEnabled());
		userEntity.setAddress(userDTO.getAddress());
		return userEntity;
	}

	public static UserEntity convertUserEntityByUserDTOADD(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setAlias(userDTO.getAlias());
		userEntity.setLevel(userDTO.getLevel());
		userEntity.setLockFlag(userDTO.getLockFlag());
		userEntity.setDepartment(userDTO.getDepartment());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setEnabled(userDTO.isEnabled());
		userEntity.setAddress(userDTO.getAddress());
		return userEntity;
	}

	public static UserDTO convertUserDTOByUserEntity(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		//userDTO.setPassword(userEntity.getPassword());
		userDTO.setAlias(userEntity.getAlias());
		userDTO.setLevel(userEntity.getLevel());
		userDTO.setLockFlag(userEntity.getLockFlag());
		userDTO.setDepartment(userEntity.getDepartment());
		userDTO.setPhone(userEntity.getPhone());
		userDTO.setEnabled(userEntity.isEnabled());
		userDTO.setAddress(userEntity.getAddress());
		return userDTO;
	}

	public static UserDTO convertUserDTOByUserEntityToMain(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setAlias(userEntity.getAlias());
		userDTO.setLevel(userEntity.getLevel());
		userDTO.setLockFlag(userEntity.getLockFlag());
		userDTO.setDepartment(userEntity.getDepartment());
		userDTO.setPhone(userEntity.getPhone());
		userDTO.setEnabled(userEntity.isEnabled());
		userDTO.setAddress(userEntity.getAddress());
		return userDTO;
	}

}
