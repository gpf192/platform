package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;

@Repository
public interface DepartmentRepository {
	//DepartmentEntity findDepartmentById(long id);
	DepartmentEntity findDepartmentByCode(String code);
	public List<DepartmentEntity> findAll();
}
