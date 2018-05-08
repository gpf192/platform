package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.UserDTO;
import cn.xsdzq.platform.model.UserRoleDTO;

public interface IUserService {

	public UserEntity findUserById(long id);

	public UserEntity findUserByName(String name);

	public List<UserDTO> findAll();

	public void addUser(UserEntity userEntity);

	public void modifyUser(UserEntity userEntity);

	public void deleteUser(UserEntity userEntity);

	public void addRoles(UserRoleDTO userRoleDTO);

	public void modifyRoles(UserRoleDTO userRoleDTO);

}
