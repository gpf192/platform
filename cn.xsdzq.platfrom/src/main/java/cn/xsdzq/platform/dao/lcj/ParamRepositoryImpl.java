package cn.xsdzq.platform.dao.lcj;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.ParamEntity;

@Repository
@Transactional(readOnly = true)
public class ParamRepositoryImpl implements ParamRepository{

	@PersistenceContext
	private EntityManager em;
	@Override
	public ParamEntity getValueByCode(String code) {
		// TODO Auto-generated method stub
		TypedQuery<ParamEntity> sqlQuery = em.createQuery("SELECT c FROM ParamEntity c WHERE c.code=?", ParamEntity.class);
		sqlQuery.setParameter(1, code);
		return sqlQuery.getSingleResult();
	}

}
