package cn.xsdzq.platform.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.AuthorityEntity;

@Repository
@Transactional(readOnly = true)
public class AuthorityRepositoryImpl implements AuthorityRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public AuthorityEntity findAuthorityById(long id) {
		// TODO Auto-generated method stub
		TypedQuery<AuthorityEntity> sqlQuery = em.createQuery("SELECT a FROM AuthorityEntity a WHERE a.id=?",
				AuthorityEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}

	@Override
	public List<AuthorityEntity> findAllAuthority() {
		// TODO Auto-generated method stub
		TypedQuery<AuthorityEntity> sqlQuery = em.createQuery("SELECT a FROM AuthorityEntity a", AuthorityEntity.class);
		return sqlQuery.getResultList();
	}

	@Override
	public AuthorityEntity findAuthorityByUrl(String url) {
		// TODO Auto-generated method stub
		AuthorityEntity authorityEntity = null;
		String likeUrl = "%" + url + "%";
		TypedQuery<AuthorityEntity> sqlQuery = em.createQuery("SELECT a FROM AuthorityEntity a WHERE a.url like ?",
				AuthorityEntity.class);
		sqlQuery.setParameter(1, likeUrl);
		List<AuthorityEntity> authorityEntities = sqlQuery.getResultList();
		if (authorityEntities.size() > 0) {
			authorityEntity = authorityEntities.get(0);
		}
		return authorityEntity;
	}

	@Override
	public void addAuthority(AuthorityEntity authorityEntity) {
		// TODO Auto-generated method stub
		em.persist(authorityEntity);
	}

}
