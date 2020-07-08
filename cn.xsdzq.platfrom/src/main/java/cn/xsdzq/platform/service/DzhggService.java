package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.DzhggEntity;

public interface DzhggService {
	int countAll();
	List<DzhggEntity> getAll(int pageNumber, int pageSize);
}
