package cn.xsdzq.platform.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;

@Repository
@Transactional(readOnly = true)
public class RoleRepositoryImpl implements RoleRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public RoleEntity findRoleById(long id) {
		// TODO Auto-generated method stub
		TypedQuery<RoleEntity> sqlQuery = em.createQuery("SELECT r FROM RoleEntity r WHERE r.id=?", RoleEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}

	@Override
	public List<RoleEntity> findAllRole() {
		// TODO Auto-generated method stub
		TypedQuery<RoleEntity> sqlQuery = em.createQuery("SELECT r FROM RoleEntity r", RoleEntity.class);
		return sqlQuery.getResultList();
	}

	@Override
	public List<AuthorityEntity> getAuthoritiesByRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		em.persist(roleEntity);
	}

	@Override
	@Transactional
	public void modifyRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		em.merge(roleEntity);
	}

	@Override
	@Transactional
	public void deteleRole(RoleEntity roleEntity) {
		// TODO Auto-generated method stub
		RoleEntity removeRoleEntity = em.find(RoleEntity.class, roleEntity.getId());
		em.remove(removeRoleEntity);
	}

	@Override
	@Transactional
	public void appendAuthorities(RoleEntity roleEntity, Set<AuthorityEntity> authorityEntities) {
		// TODO Auto-generated method stub
		roleEntity.setAuthorityEntities(authorityEntities);
		em.merge(roleEntity);
	}

}
