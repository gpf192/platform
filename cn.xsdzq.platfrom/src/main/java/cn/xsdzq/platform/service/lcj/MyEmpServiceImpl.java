package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyEmpRepository;
import cn.xsdzq.platform.entity.lcj.EmpEntity;

@Service(value = "myEmpServiceImpl")
@Transactional(readOnly = true)
public class MyEmpServiceImpl implements MyEmpService{
	@Autowired
	private MyEmpRepository myEmpRepository;
	@Override
	public List<EmpEntity> getAllEmp(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByOrderById(pageRequest);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myEmpRepository.count();
	}
}
