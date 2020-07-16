package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.AwardEntity;

@Repository
@Transactional(readOnly = true)
public class AwardRepositoryImpl implements AwardRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AwardEntity> getAllAward() {
		// TODO Auto-generated method stub
		TypedQuery<AwardEntity> sqlQuery = em.createQuery(
				"SELECT c FROM AwardEntity c ", AwardEntity.class);
		
		return sqlQuery.getResultList();
	}

	@Override
	public AwardEntity getAwardById(long id) {
		// TODO Auto-generated method stub
		TypedQuery<AwardEntity> sqlQuery = em.createQuery("SELECT c FROM AwardEntity c WHERE c.id=?", AwardEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}
	@Override
	@Transactional
	public void deleteAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		AwardEntity deleteInfo = em.find(AwardEntity.class, id);
		em.remove(deleteInfo);
	}
	@Override
	@Transactional
	public void addAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		AwardEntity info = em.find(AwardEntity.class, id);
		if(info == null){
			em.persist(entity);
			
		}else {
			em.merge(entity);
			}
		
	}
	
	@Override
	@Transactional
	public void modifyAward(AwardEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}
}
