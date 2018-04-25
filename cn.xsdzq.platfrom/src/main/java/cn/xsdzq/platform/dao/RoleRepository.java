package cn.xsdzq.platform.dao;

import java.util.List;
import java.util.Set;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;

public interface RoleRepository {

	public RoleEntity findRoleById(long id);

	public List<RoleEntity> findAllRole();

	public void addRole(RoleEntity roleEntity);

	public List<AuthorityEntity> getAuthoritiesByRole(RoleEntity roleEntity);

	public void appendAuthorities(RoleEntity roleEntity, Set<AuthorityEntity> authorityEntities);

}
