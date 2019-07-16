package cn.xsdzq.platform.dao.lcj;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;

@Repository
public interface EmpVoteRepository {
	void addWeight(EmpTicketRecordEntity entity);
}
