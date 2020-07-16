package cn.xsdzq.platform.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.CustomerMobileEntity;

@Repository
@Transactional(readOnly = true)
public class KCRepositoryImpl implements KCRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void addInfo(CustomerMobileEntity customerMobileEntity) {
		em.persist(customerMobileEntity);
	}
}
