package cn.xsdzq.platform.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.RecordEntity;

@Repository
@Transactional(readOnly = true)
public class RecordRepositoryImpl implements RecordRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void addRecord(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		em.persist(recordEntity);
	}

	@Override
	@Transactional
	public void mergeRecord(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		TypedQuery<RecordEntity> sqlQuery = em.createQuery("SELECT c FROM RecordEntity c WHERE c.channel=? and c.uri=?",
				RecordEntity.class);
		// if(recordEntity.getChannel().equals(""))
		sqlQuery.setParameter(1, recordEntity.getChannel());
		sqlQuery.setParameter(2, recordEntity.getUri());
		List<RecordEntity> rList = sqlQuery.getResultList();
		if (rList.size() > 0) {
			RecordEntity oldRecordEntity = rList.get(0);
			int acount = oldRecordEntity.getAcount();
			oldRecordEntity.setAcount(acount + 1);
			em.merge(oldRecordEntity);
			em.flush();
		} else {
			recordEntity.setAcount(1);
			em.persist(recordEntity);
		}
	}

}
