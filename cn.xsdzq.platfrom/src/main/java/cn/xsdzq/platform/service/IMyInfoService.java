package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.InfoEntity;

public interface IMyInfoService {

	List<InfoEntity> getInfosByCategoryId(long id);

	List<InfoEntity> getInfosByCategoryId(long id, int pageNumber, int pageSize);

	int countInfosByCategoryId(long categoryId);

}
