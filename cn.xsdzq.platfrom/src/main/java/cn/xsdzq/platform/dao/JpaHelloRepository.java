package cn.xsdzq.platform.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.HelloEntity;

@Repository
@Transactional
public class JpaHelloRepository implements HelloRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addHello(HelloEntity helloEntity) {
		// TODO Auto-generated method stub
		// helloEntity.toString();
		System.out.println(helloEntity.getName());
		// entityManager.getTransaction().begin();
		entityManager.persist(helloEntity);
		entityManager.flush();
		// entityManager.getTransaction().commit();
		System.out.println("add hello");
	}

	@Override
	public HelloEntity findOne() {
		// TODO Auto-generated method stub
		Long id = 1L;
		HelloEntity entity = entityManager.find(HelloEntity.class, id);
		System.out.println("findOne: " + entity.getName());
		return null;
	}

}
