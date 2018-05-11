package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.AuthorityRepository;
import cn.xsdzq.platform.dao.RoleRepository;
import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.model.RoleDTO;

@Service(value = "authorityServiceImpl")
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements IAuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AuthorityEntity findAuthorityById(long id) {
		// TODO Auto-generated method stub
		return authorityRepository.findAuthorityById(id);
	}

	@Override
	public List<AuthorityEntity> getAllAuthority() {
		// TODO Auto-generated method stub
		return authorityRepository.findAllAuthority();
	}

	@Override
	public List<AuthorityEntity> getAllByRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		RoleEntity roleEntity = roleRepository.findRoleById(roleDTO.getId());
		Set<AuthorityEntity> rSet = roleEntity.getAuthorityEntities();
		List<AuthorityEntity> all = authorityRepository.findAllAuthority();
		List<AuthorityEntity> authorityEntities = new ArrayList<>();
		for (AuthorityEntity authorityEntity : all) {
			if (!rSet.contains(authorityEntity)) {
				authorityEntities.add(authorityEntity);
			}
		}
		return authorityEntities;
	}

	@Override
	@Transactional
	public void addAuthority(AuthorityEntity authorityEntity) {
		// TODO Auto-generated method stub
		authorityRepository.addAuthority(authorityEntity);
	}

}
