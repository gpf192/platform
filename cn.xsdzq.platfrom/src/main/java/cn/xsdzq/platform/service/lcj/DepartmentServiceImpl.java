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
	public DepartmentEntity findDepartmentById(long id) {
		// TODO Auto-generated method stub
		DepartmentEntity entity = departmentRepository.findDepartmentById(id);
		return entity;
	}

	@Override
	public List<DepartmentEntity> findAll() {
		// TODO Auto-generated method stub
		List<DepartmentEntity> list = departmentRepository.findAll();
		return list;
	}
}
