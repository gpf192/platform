package cn.xsdzq.platform.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.InfoEntity;

@Repository
@Transactional(readOnly = true)
public class InfoRepositoryImpl implements InfoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public InfoEntity getInfo(long id) {
		// TODO Auto-generated method stub
		TypedQuery<InfoEntity> sqlQuery = em.createQuery("SELECT c FROM InfoEntity c WHERE c.id=?", InfoEntity.class);
		sqlQuery.setParameter(1, id);
		return sqlQuery.getSingleResult();
	}

	@Override
	public List<InfoEntity> getInfosByCategoryId(long id) {
		// TODO Auto-generated method stub
		TypedQuery<InfoEntity> sqlQuery = em.createQuery(
				"SELECT c FROM InfoEntity c WHERE c.categoryId = ? and c.created_by = ?", InfoEntity.class);
		sqlQuery.setParameter(1, id);
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		User user = (User) auth.getPrincipal();
		String name = user.getUsername();
		sqlQuery.setParameter(2, name);
		return sqlQuery.getResultList();
	}

	@Override
	public List<InfoEntity> getInfosByCategoryIdToFront(long id) {
		// TODO Auto-generated method stub
		TypedQuery<InfoEntity> sqlQuery = em.createQuery(
				"SELECT c FROM InfoEntity c WHERE c.categoryId = ? and c.checked_result = ?", InfoEntity.class);
		sqlQuery.setParameter(1, id);
		sqlQuery.setParameter(2, "approve");// 前台页面只显示审核通过的
		return sqlQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoEntity> searchInfos(String key) {
		// TODO Auto-generated method stub
		String param = "%" + key + "%";
		String sql = "SELECT c FROM InfoEntity c WHERE c.title like ? and c.checked_result = ?";
		Query query = em.createQuery(sql, InfoEntity.class);
		query.setParameter(1, param);
		query.setParameter(2, "approve");// 前台搜索框只搜审核通过的
		List<InfoEntity> infos = query.getResultList();
		return infos;
	}

	@Override
	@Transactional
	public void deleteInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		System.out.println("deleteInfo");
		long id = infoEntity.getId();
		System.out.println(id);
		InfoEntity deleteInfo = em.find(InfoEntity.class, id);
		em.remove(deleteInfo);
	}

	@Override
	@Transactional
	public void addInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		em.persist(infoEntity);
	}

	@Override
	@Transactional
	public void modifyInfo(InfoEntity infoEntity) {
		// TODO Auto-generated method stub
		// Info modifyInfo = em.find(Info.class, info.getId());
		em.merge(infoEntity);
	}

	// add by fjx begin
	@Override
	public List<InfoEntity> searUncheckchInfos() {
		// TODO Auto-generated method stub
		TypedQuery<InfoEntity> sqlQuery = em.createQuery(
				"SELECT c FROM InfoEntity c WHERE c.checked = ? and c.checked_result = ?", InfoEntity.class);
		sqlQuery.setParameter(1, "N");
		sqlQuery.setParameter(2, "submit");
		return sqlQuery.getResultList();
	}

	@Override
	public String getCheckResult(long id) {
		// TODO Auto-generated method stub
		TypedQuery<InfoEntity> sqlQuery = em.createQuery("SELECT c FROM InfoEntity c WHERE c.id = ?", InfoEntity.class);
		sqlQuery.setParameter(1, id);
		InfoEntity infoEntity = sqlQuery.getSingleResult();
		return infoEntity.getCheckedResult();
	}
	// add by fjx end
}
