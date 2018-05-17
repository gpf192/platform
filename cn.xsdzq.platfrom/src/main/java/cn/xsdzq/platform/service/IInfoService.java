package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.InfoEntity;

public interface IInfoService {

	InfoEntity getInfo(long id);

	void addInfo(InfoEntity infoEntity);

	void deleteInfo(InfoEntity infoEntity);

	void modifyInfo(InfoEntity infoEntity);

	List<InfoEntity> getInfosByCategoryId(long id);

	List<InfoEntity> getInfosByCategoryIdToFront(long id);

	List<InfoEntity> searchInfos(String key);

	// add by fjx begin
	List<InfoEntity> searUncheckchInfos();

	String getCheckResult(long id);

	void modifyCheckResult(long id, boolean flag);
	// add by fjx end
}
