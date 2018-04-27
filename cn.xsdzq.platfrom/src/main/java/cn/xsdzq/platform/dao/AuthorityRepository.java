package cn.xsdzq.platform.dao;

import java.util.List;

import cn.xsdzq.platform.entity.AuthorityEntity;

public interface AuthorityRepository {

	public AuthorityEntity findAuthorityById(long id);

	public AuthorityEntity findAuthorityByAuthority(String name);

	public List<AuthorityEntity> findAllAuthority();

	public AuthorityEntity findAuthorityByUrl(String url);

	public void addAuthority(AuthorityEntity authorityEntity);

}
