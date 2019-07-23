package cn.xsdzq.platform.service.lcj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.EmpVoteRepository;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

@Service(value = "empVoteServiceImpl")
@Transactional(readOnly = true)
public class EmpVoteServiceImpl implements EmpVoteService{
	Logger logger = LogManager.getLogger(EmpVoteServiceImpl.class.getName());
	@Autowired
	private EmpVoteRepository empVoteRepository;
	
	@Override
	@Transactional
	public void addWeight(EmpTicketEntity entity) {
		// TODO Auto-generated method stub
		empVoteRepository.addWeight(entity);
	}

}
