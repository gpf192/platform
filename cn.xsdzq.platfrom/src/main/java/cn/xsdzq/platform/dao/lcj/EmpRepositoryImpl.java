package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;

@Repository
@Transactional(readOnly = true)
public class EmpRepositoryImpl implements EmpRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<EmpEntity> getAllEmp() {
		// TODO Auto-generated method stub
		TypedQuery<EmpEntity> sqlQuery = em.createQuery(
				"SELECT c FROM EmpEntity c ", EmpEntity.class);
		
		return sqlQuery.getResultList();
	
	}

	@Override
	@Transactional
	public void deleteEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		EmpEntity deleteInfo = em.find(EmpEntity.class, id);
		deleteInfo.setEnable(0);
		em.merge(deleteInfo);
		//em.remove(deleteInfo);
	}

	@Override
	@Transactional
	public void addEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}

	@Override
	@Transactional
	public void modifyEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}

	@Override
	@Transactional
	public void addEmpTicket(EmpTicketEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}
	
	@Override
	public void modifyEmpTicket(EmpTicketEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}

	@Override
	@Transactional
	public void deleteEmpTicket(EmpTicketEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		EmpEntity deleteInfo = em.find(EmpEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	public EmpTicketEntity getEmpTicketByEmpId(String id) {
		// TODO Auto-generated method stub
		TypedQuery<EmpTicketEntity> sqlQuery = em.createQuery("SELECT c FROM EmpTicketEntity c WHERE c.id=?", EmpTicketEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}

	@Override
	public EmpEntity getEmpByEmpCode(String empCode) {
		// TODO Auto-generated method stub
		TypedQuery<EmpEntity> sqlQuery = em.createQuery("SELECT c FROM EmpEntity c WHERE c.empCode=?", EmpEntity.class);
		sqlQuery.setParameter(1, empCode);
		return sqlQuery.getSingleResult();
	}


}
