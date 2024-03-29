package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;

@Repository
@Transactional(readOnly = true)
public class DepartmentRepositoryImpl implements DepartmentRepository{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DepartmentEntity> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<DepartmentEntity> sqlQuery = em.createQuery("SELECT c FROM DepartmentEntity c", DepartmentEntity.class);
		return sqlQuery.getResultList();
	}

	@Override
	public DepartmentEntity findDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		TypedQuery<DepartmentEntity> sqlQuery = em.createQuery("SELECT c FROM DepartmentEntity c WHERE c.code=?",
				DepartmentEntity.class);
		sqlQuery.setParameter(1, code);
		return sqlQuery.getSingleResult();
	}
}
