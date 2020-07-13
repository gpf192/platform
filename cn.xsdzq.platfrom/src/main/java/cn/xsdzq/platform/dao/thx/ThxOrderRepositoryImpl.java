package cn.xsdzq.platform.dao.thx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

@Repository
@Transactional(readOnly = true)
public class ThxOrderRepositoryImpl implements ThxOrderRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void addInfo(ThxOrderEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}

	@Override
	@Transactional
	public void modifyInfo(ThxOrderEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);

	}

	@Override
	public List<ThxOrderEntity> getEntityByOrderId(String orderId) {
		// TODO Auto-generated method stub
		TypedQuery<ThxOrderEntity> sqlQuery = em.createQuery(
				"SELECT c FROM ThxOrderEntity c WHERE c.orderId = ?", ThxOrderEntity.class);
		sqlQuery.setParameter(1, orderId);				
		return sqlQuery.getResultList();
	}

}
