package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface MyEmpService {
	int countAll();
	List<EmpEntity> getAllEmp(int pageNumber, int pageSize);
}
