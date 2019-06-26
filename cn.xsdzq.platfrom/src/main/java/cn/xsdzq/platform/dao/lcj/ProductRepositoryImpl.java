package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.lcj.ProductEntity;

@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl implements ProductRepository{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		TypedQuery<ProductEntity> sqlQuery = em.createQuery(
				"SELECT c FROM ProductEntity c ", ProductEntity.class);
		
		return sqlQuery.getResultList();
	}
	@Override
	@Transactional
	public void deleteProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		long id = entity.getId();
		ProductEntity deleteInfo = em.find(ProductEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	@Transactional
	public void addProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		em.persist(entity);
	}

	@Override
	@Transactional
	public void modifyProduct(ProductEntity entity) {
		// TODO Auto-generated method stub
		em.merge(entity);
	}

}
