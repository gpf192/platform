package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

public interface EmpService {
	List<EmpEntity> getAllPrize();
	void addEmp(EmpEntity entity);

	void deleteEmp(EmpEntity entity);

	void modifyEmp(EmpEntity entity);
}
