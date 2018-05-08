package cn.xsdzq.platform.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserEntity findUserById(long id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = null;
		TypedQuery<UserEntity> sqlQuery = em.createQuery("SELECT u FROM UserEntity u WHERE u.id=?", UserEntity.class);
		sqlQuery.setParameter(1, id);
		List<UserEntity> userEntities = sqlQuery.getResultList();
		if (userEntities.size() > 0) {
			userEntity = userEntities.get(0);
		}
		return userEntity;
	}

	@Override
	public UserEntity findUserByName(String name) {
		// TODO Auto-generated method stub

		TypedQuery<UserEntity> sqlQuery = em.createQuery("SELECT u FROM UserEntity u WHERE u.username=?",
				UserEntity.class);
		sqlQuery.setParameter(1, name);
		return sqlQuery.getSingleResult();
	}

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<UserEntity> sqlQuery = em.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
		return sqlQuery.getResultList();
	}

	@Override
	@Transactional
	public void addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		em.persist(userEntity);
	}

	@Override
	@Transactional
	public void modifyUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		em.merge(userEntity);
	}

	@Override
	@Transactional
	public void deleteUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		UserEntity uEntity = em.find(UserEntity.class, userEntity.getId());
		em.remove(uEntity);
	}

	@Override
	@Transactional
	public void addRoles(UserEntity userEntity, Set<RoleEntity> roleEntities) {
		// TODO Auto-generated method stub
		System.out.println("dao: " + roleEntities.size());
		userEntity.setRoleEntities(roleEntities);
		em.merge(userEntity);
	}

}
