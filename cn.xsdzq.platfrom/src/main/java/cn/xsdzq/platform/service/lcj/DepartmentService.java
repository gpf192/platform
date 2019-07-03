package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;

public interface DepartmentService {
	DepartmentEntity findDepartmentById(long id);
	DepartmentEntity findDepartmentByCode(String code);
	public List<DepartmentEntity> findAll();
}
