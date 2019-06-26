package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
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
	public void deleteEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		EmpEntity deleteInfo = em.find(EmpEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	public void addEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}

	@Override
	public void modifyEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}

}
