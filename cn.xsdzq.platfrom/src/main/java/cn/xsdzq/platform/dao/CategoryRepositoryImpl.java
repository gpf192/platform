package cn.xsdzq.platform.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.CategoryEntity;

@Repository
@Transactional(readOnly = true)
public class CategoryRepositoryImpl implements CategoryRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public CategoryEntity findCategoryById(long id) {
		// TODO Auto-generated method stub
		TypedQuery<CategoryEntity> sqlQuery = em.createQuery("SELECT c FROM Category c WHERE c.id=?",
				CategoryEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}

	@Override
	public List<CategoryEntity> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<CategoryEntity> sqlQuery = em.createQuery("SELECT c FROM Category c", CategoryEntity.class);
		return sqlQuery.getResultList();
	}

	@Override
	@Transactional
	public void addCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		em.persist(categoryEntity);
	}

	@Override
	@Transactional
	public void modifyCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		em.merge(categoryEntity);
	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		// TODO Auto-generated method stub
		CategoryEntity categoryEntity = em.find(CategoryEntity.class, id);
		em.remove(categoryEntity);
	}

}
