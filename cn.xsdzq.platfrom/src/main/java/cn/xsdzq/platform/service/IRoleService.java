package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.model.RoleAuthorityDTO;
import cn.xsdzq.platform.model.RoleDTO;
import cn.xsdzq.platform.model.UserDTO;

public interface IRoleService {

	public RoleEntity findRoleById(long id);

	public List<RoleDTO> findExtraRole(UserDTO userDTO);

	public List<RoleDTO> findMyRole(UserDTO userDTO);

	public List<RoleEntity> findAllRole();

	public void addRole(RoleEntity roleEntity);

	public void appendAuthorities(RoleAuthorityDTO roleAuthorityDTO);

}
