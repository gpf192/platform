package cn.xsdzq.platform.model;

import java.util.List;

import cn.xsdzq.platform.entity.AuthorityEntity;

public class RoleAuthorityDTO {

	private long role_id;
	private List<AuthorityEntity> authorityEntities;

	public RoleAuthorityDTO() {

	}

	public RoleAuthorityDTO(long role_id, List<AuthorityEntity> authorityEntities) {
		this.role_id = role_id;
		this.authorityEntities = authorityEntities;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}

	public List<AuthorityEntity> getAuthorityEntities() {
		return authorityEntities;
	}

	public void setAuthorityEntities(List<AuthorityEntity> authorityEntities) {
		this.authorityEntities = authorityEntities;
	}

}
