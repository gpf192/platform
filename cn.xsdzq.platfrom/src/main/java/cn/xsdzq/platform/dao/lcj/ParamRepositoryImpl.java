package cn.xsdzq.platform.dao.lcj;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.InfoEntity;
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
	@Override
	@Transactional
	public void deleteInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		ParamEntity deleteInfo = em.find(ParamEntity.class, id);
		em.remove(deleteInfo);
	}
	@Override
	@Transactional
	public void addInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}
	@Override
	@Transactional
	public void modifyInfo(ParamEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}

}
