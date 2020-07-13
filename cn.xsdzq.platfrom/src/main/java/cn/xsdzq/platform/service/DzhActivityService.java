package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.DzhActivityEntity;

public interface DzhActivityService {
	List<DzhActivityEntity> findByOrderByName();
	DzhActivityEntity findByName(String name);
}
