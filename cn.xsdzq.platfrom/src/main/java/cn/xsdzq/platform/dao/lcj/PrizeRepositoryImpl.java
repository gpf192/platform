package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.PrizeEntity;

@Repository
@Transactional(readOnly = true)
public class PrizeRepositoryImpl implements PrizeRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<PrizeEntity> getAllPrize() {
		// TODO Auto-generated method stub
		TypedQuery<PrizeEntity> sqlQuery = em.createQuery(
				"SELECT c FROM PrizeEntity c ", PrizeEntity.class);
		
		return sqlQuery.getResultList();
	}
	@Override
	@Transactional
	public void deletePrize(PrizeEntity prizeEntity) {
		// TODO Auto-generated method stub
		long id = prizeEntity.getId();
		PrizeEntity deleteInfo = em.find(PrizeEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	@Transactional
	public void addPrize(PrizeEntity prizeEntity) {
		// TODO Auto-generated method stub
		long id = prizeEntity.getId();
		PrizeEntity info = em.find(PrizeEntity.class, id);
		if(info == null){
			em.persist(prizeEntity);
			
		}else {
			em.merge(prizeEntity);
			}
		
	}

	@Override
	@Transactional
	public void modifyPrize(PrizeEntity prizeEntity) {
		// TODO Auto-generated method stub
		em.merge(prizeEntity);
	}
}
