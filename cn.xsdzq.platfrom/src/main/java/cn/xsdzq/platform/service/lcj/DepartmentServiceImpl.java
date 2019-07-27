package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.DepartmentRepository;
import cn.xsdzq.platform.entity.lcj.DepartmentEntity;

@Service(value = "departmentServiceImpl")
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentEntity> findAll() {
		// TODO Auto-generated method stub
		List<DepartmentEntity> list = departmentRepository.findAll();
		return list;
	}

	@Override
	public DepartmentEntity findDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		DepartmentEntity entity = departmentRepository.findDepartmentByCode(code);
		return entity;
	}
}
