package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.model.RoleDTO;

public interface IAuthorityService {

	public AuthorityEntity findAuthorityById(long id);

	public List<AuthorityEntity> getAllAuthority();

	public List<AuthorityEntity> getAllByRole(RoleDTO roleDTO);

	public void addAuthority(AuthorityEntity authorityEntity);

}
