package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyEmpVoteRepository;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;

@Service(value = "myEmpVoteServiceImpl")
@Transactional(readOnly = true)
public class MyEmpVoteServiceImpl implements MyEmpVoteService{
	@Autowired
	private MyEmpVoteRepository myEmpVoteRepository;

	@Override
	public int countAll() {
		
		return (int)myEmpVoteRepository.count();
	}

	@Override
	public List<EmpVoteEntity> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpVoteEntity> pages = myEmpVoteRepository.findByOrderById(pageRequest);
		List<EmpVoteEntity> infos = pages.getContent();
		return infos;
	}
}
