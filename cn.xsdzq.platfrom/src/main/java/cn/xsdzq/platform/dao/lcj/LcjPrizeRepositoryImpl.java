package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;

@Repository
@Transactional(readOnly = true)
public class LcjPrizeRepositoryImpl implements LcjPrizeRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<LcjPrizeEntity> getAllPrize() {
		// TODO Auto-generated method stub
		TypedQuery<LcjPrizeEntity> sqlQuery = em.createQuery(
				"SELECT c FROM LcjPrizeEntity c ", LcjPrizeEntity.class);
		
		return sqlQuery.getResultList();
	}
	@Override
	@Transactional
	public void deletePrize(LcjPrizeEntity prizeEntity) {
		// TODO Auto-generated method stub
		long id = prizeEntity.getId();
		LcjPrizeEntity deleteInfo = em.find(LcjPrizeEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	@Transactional
	public void addPrize(LcjPrizeEntity prizeEntity) {
		// TODO Auto-generated method stub
		long id = prizeEntity.getId();
		LcjPrizeEntity info = em.find(LcjPrizeEntity.class, id);
		if(info == null){
			em.persist(prizeEntity);
			
		}else {
			em.merge(prizeEntity);
			}
		
	}

	@Override
	@Transactional
	public void modifyPrize(LcjPrizeEntity prizeEntity) {
		// TODO Auto-generated method stub				
		em.merge(prizeEntity);
	}
	@Override
	public LcjPrizeEntity getPrizeById(long id) {
		// TODO Auto-generated method stub
		TypedQuery<LcjPrizeEntity> sqlQuery = em.createQuery("SELECT c FROM LcjPrizeEntity c WHERE c.id=?", LcjPrizeEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}
}
