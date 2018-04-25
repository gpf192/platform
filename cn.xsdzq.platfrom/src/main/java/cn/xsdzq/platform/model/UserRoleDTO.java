package cn.xsdzq.platform.model;

import java.util.List;

public class UserRoleDTO {
	private long user_id;
	private List<RoleDTO> roleDTOs;

	public UserRoleDTO() {

	}

	public UserRoleDTO(long user_id, List<RoleDTO> roleDTOs) {
		this.user_id = user_id;
		this.roleDTOs = roleDTOs;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public List<RoleDTO> getRoleDTOs() {
		return roleDTOs;
	}

	public void setRoleDTOs(List<RoleDTO> roleDTOs) {
		this.roleDTOs = roleDTOs;
	}

}
