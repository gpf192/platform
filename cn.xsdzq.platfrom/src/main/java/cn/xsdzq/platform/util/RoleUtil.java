package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.model.RoleDTO;

public class RoleUtil {

	public static RoleEntity convertRoleEntityByRoleDTO(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(roleDTO.getId());
		roleEntity.setName(roleDTO.getName());
		roleEntity.setDescribe(roleDTO.getDescribe());
		return roleEntity;
	}

	public static RoleEntity convertRoleEntityByRoleDTOADD(RoleDTO roleDTO) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName(roleDTO.getName());
		roleEntity.setDescribe(roleDTO.getDescribe());
		return roleEntity;
	}

	public static RoleDTO convertRoleDTOByRoleEntity(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleEntity.getId());
		roleDTO.setName(roleEntity.getName());
		roleDTO.setDescribe(roleEntity.getDescribe());
		return roleDTO;
	}

}
