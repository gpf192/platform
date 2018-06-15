package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.AuthorityRepository;
import cn.xsdzq.platform.dao.RoleRepository;
import cn.xsdzq.platform.dao.UserRepository;
import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.RoleAuthorityDTO;
import cn.xsdzq.platform.model.RoleDTO;
import cn.xsdzq.platform.model.UserDTO;
import cn.xsdzq.platform.util.RoleUtil;

@Service(value = "roleServiceImpl")
@Transactional(readOnly = true)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public RoleEntity findRoleById(long id) {
		// TODO Auto-generated method stub
		return roleRepository.findRoleById(id);
	}

	@Override
	public List<RoleDTO> findExtraRole(UserDTO userDTO) {
		// TODO Auto-generated method stub

		UserEntity userEntity = userRepository.findUserById(userDTO.getId());
		Set<RoleEntity> myRoleEntities = userEntity.getRoleEntities();
		List<RoleEntity> roleEntities = roleRepository.findAllRole();
		List<RoleDTO> rList = new ArrayList<>();
		for (RoleEntity roleEntity : roleEntities) {
			if (!myRoleEntities.contains(roleEntity)) {
				rList.add(RoleUtil.convertRoleDTOByRoleEntity(roleEntity));
			}
		}
		return rList;
	}

	@Override
	public List<RoleDTO> findMyRole(UserDTO userDTO) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findUserById(userDTO.getId());
		Set<RoleEntity> myRoleEntities = userEntity.getRoleEntities();
		List<RoleEntity> roleEntities = roleRepository.findAllRole();
		List<RoleDTO> rList = new ArrayList<>();
		for (RoleEntity roleEntity : roleEntities) {
			RoleDTO roleDTO = RoleUtil.convertRoleDTOByRoleEntity(roleEntity);
			if (myRoleEntities.contains(roleEntity)) {
				roleDTO.setCheck(true);
				rList.add(roleDTO);
			} else {
				roleDTO.setCheck(false);
				rList.add(roleDTO);
			}
		}
		return rList;
	}

	@Override
	public List<AuthorityEntity> findAuthorityByRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		RoleEntity roleEntity = roleRepository.findRoleById(roleDTO.getId());
		List<AuthorityEntity> authorityEntities = authorityRepository.findAllAuthority();
		Set<AuthorityEntity> myAuthoritySet = roleEntity.getAuthorityEntities();
		for (AuthorityEntity authorityEntity : authorityEntities) {
			if (myAuthoritySet.contains(authorityEntity)) {
				authorityEntity.setCheck(true);
			} else {
				authorityEntity.setCheck(false);
			}
		}
		return authorityEntities;
	}

	@Override
	public List<RoleEntity> findAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAllRole();
	}

	@Override
	@Transactional
	public void addRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		AuthorityEntity authorityEntity = authorityRepository.findAuthorityByAuthority("ROLE_MAIN_AUTHORITY");
		Set<AuthorityEntity> authorityEntities = new HashSet<>();
		if (authorityEntity != null) {
			authorityEntities.add(authorityEntity);
			roleEntity.setAuthorityEntities(authorityEntities);
		}
		roleRepository.addRole(roleEntity);
	}

	@Override
	@Transactional
	public void modifyRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		roleRepository.modifyRole(roleEntity);
	}

	@Override
	@Transactional
	public void deteleRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		roleRepository.deteleRole(roleEntity);
	}

	@Override
	@Transactional
	public void modifyRoleAuthority(RoleAuthorityDTO roleAuthorityDTO) {
		// TODO Auto-generated method stub
		RoleEntity roleEntity = roleRepository.findRoleById(roleAuthorityDTO.getRole_id());
		List<AuthorityEntity> aList = roleAuthorityDTO.getAuthorityEntities();
		List<AuthorityEntity> myAuthorityEntities = new ArrayList<>();
		for (AuthorityEntity aEntity : aList) {
			if (aEntity.isCheck()) {
				myAuthorityEntities.add(aEntity);
			}
		}
		Set<AuthorityEntity> myAuthoritySet = getAuthorityEntitieSet(myAuthorityEntities);
		roleEntity.setAuthorityEntities(myAuthoritySet);
		roleRepository.modifyRole(roleEntity);
	}

	@Override
	@Transactional
	public void appendAuthorities(RoleAuthorityDTO roleAuthorityDTO) {
		// TODO Auto-generated method stub
		// roleRepository.appendAuthorities(roleEntity, authorityEntities);
		RoleEntity roleEntity = roleRepository.findRoleById(roleAuthorityDTO.getRole_id());
		Set<AuthorityEntity> myAuthoritySet = roleEntity.getAuthorityEntities();
		Set<AuthorityEntity> authoritySet = new HashSet<AuthorityEntity>();
		List<AuthorityEntity> aList = roleAuthorityDTO.getAuthorityEntities();
		if (roleEntity != null) {

			for (AuthorityEntity authorityEntity : aList) {
				if (authorityEntity.getLevel() == 1) {
					authoritySet.add(authorityEntity);
				}

				if (authorityEntity.getLevel() == 2) {
					authoritySet.add(authorityEntity);
					AuthorityEntity parentAuthority = authorityRepository
							.findAuthorityById(authorityEntity.getParent_id());
					if (myAuthoritySet.contains(parentAuthority) || authoritySet.contains(parentAuthority)) {
						// 保护父权限的处理逻辑
					} else {
						authoritySet.add(parentAuthority);
					}
				}
				if (authorityEntity.getLevel() == 3) {
					authoritySet.add(authorityEntity);
					// level 为 2 的逻辑
					AuthorityEntity level2Authority = authorityRepository
							.findAuthorityById(authorityEntity.getParent_id());
					if (myAuthoritySet.contains(level2Authority) || authoritySet.contains(level2Authority)) {
						// 保护父权限的处理逻辑
					} else {
						authoritySet.add(level2Authority);
					}
					AuthorityEntity level3Authority = authorityRepository
							.findAuthorityById(level2Authority.getParent_id());
					if (myAuthoritySet.contains(level3Authority) || authoritySet.contains(level3Authority)) {
						// 保护父权限的处理逻辑
					} else {
						authoritySet.add(level3Authority);
					}
				}
			}
			if (authoritySet.size() > 0) {
				myAuthoritySet.addAll(authoritySet);
				roleRepository.appendAuthorities(roleEntity, myAuthoritySet);
			}
		}
	}

	private Set<AuthorityEntity> getAuthorityEntitieSet(List<AuthorityEntity> authorityEntities) {
		Set<AuthorityEntity> authoritySet = new HashSet<AuthorityEntity>();
		List<AuthorityEntity> aList = authorityEntities;
		if (aList != null) {
			for (AuthorityEntity authorityEntity : aList) {
				if (authorityEntity.getLevel() == 3) {
					authoritySet.add(authorityEntity);
					// level 为 2 的逻辑
					AuthorityEntity level2Authority = authorityRepository
							.findAuthorityById(authorityEntity.getParent_id());
					if (authoritySet.contains(level2Authority)) {
						// 保护父权限的处理逻辑
					} else {
						authoritySet.add(level2Authority);
					}
					AuthorityEntity level3Authority = authorityRepository
							.findAuthorityById(level2Authority.getParent_id());
					if (authoritySet.contains(level3Authority)) {
						// 保护父权限的处理逻辑
					} else {
						authoritySet.add(level3Authority);
					}
				}
			}
		}
		// 修改权限要赋予基础权限
		AuthorityEntity mainAuthorityEntity = authorityRepository.findAuthorityByAuthority("ROLE_MAIN_AUTHORITY");
		authoritySet.add(mainAuthorityEntity);
		return authoritySet;
	}

}
