package cn.xsdzq.platform.dao;

import java.util.List;
import java.util.Set;

import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;

public interface UserRepository {

	public UserEntity findUserById(long id);

	public UserEntity findUserByName(String name);

	public List<UserEntity> findAll();

	public void addUser(UserEntity userEntity);

	public void deleteUser(UserEntity userEntity);

	public void addRoles(UserEntity userEntity, Set<RoleEntity> roleEntities);

}
