package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;

public interface DepartmentService {
	DepartmentEntity findDepartmentById(long id);
	public List<DepartmentEntity> findAll();
}
