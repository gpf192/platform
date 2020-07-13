package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import cn.xsdzq.platform.entity.DzhActivityEntity;

public interface DzhActivityRepository extends JpaRepository<DzhActivityEntity, Long> {
	List<DzhActivityEntity> findByOrderByName();
	DzhActivityEntity findByName(String name);
}
