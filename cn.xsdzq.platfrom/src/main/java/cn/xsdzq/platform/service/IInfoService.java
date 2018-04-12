package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.InfoEntity;

public interface IInfoService {

	InfoEntity getInfo(long id);

	void addInfo(InfoEntity infoEntity);

	void deleteInfo(InfoEntity infoEntity);

	void modifyInfo(InfoEntity infoEntity);

	List<InfoEntity> getInfosByCategoryId(long id);

	List<InfoEntity> searchInfos(String key);

}
