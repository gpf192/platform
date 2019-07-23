package cn.xsdzq.platform.dao.lcj;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;
@Repository
@Transactional(readOnly = true)
public class EmpVoteRepositoryImpl implements EmpVoteRepository{

	@PersistenceContext
	private EntityManager em;
	@Override
	public void addWeight(EmpTicketEntity entity) {
		// TODO Auto-generated method stub
		EmpTicketEntity entity1 = em.find(EmpTicketEntity.class, entity.getId());
		entity1.setWeight(entity.getWeight());
		em.merge(entity1);
	}
}
