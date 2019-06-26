package cn.xsdzq.platform.dao.lcj;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.EmpEntity;

@Repository
public interface EmpRepository {
	List<EmpEntity> getAllEmp();
	void deleteEmp(EmpEntity entity);

	void addEmp(EmpEntity entity);

	void modifyEmp(EmpEntity entity);
}
