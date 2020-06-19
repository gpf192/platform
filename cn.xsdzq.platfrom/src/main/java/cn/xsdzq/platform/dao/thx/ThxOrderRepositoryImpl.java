package cn.xsdzq.platform.dao.thx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
