package cn.xsdzq.platform.dao.mall;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.DepartmentEntity;


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

	DepartmentEntity findByCode(String code);

}
