package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;


public interface EmpTicketRecordRepository extends JpaRepository<EmpTicketRecordEntity, Long> {
	Page<EmpTicketRecordEntity> findByOrderByRecordTime(Pageable pageable);
}
