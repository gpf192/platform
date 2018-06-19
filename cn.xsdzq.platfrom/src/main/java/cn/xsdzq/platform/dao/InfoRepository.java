package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.InfoEntity;

@Repository
public interface InfoRepository {
	InfoEntity getInfo(long id);

	List<InfoEntity> getInfosByCategoryId(long id);

	List<InfoEntity> getInfosByCategoryIdToFront(long id);

	List<InfoEntity> searchInfos(String key);

	void deleteInfo(InfoEntity infoEntity);

	void addInfo(InfoEntity infoEntity);

	void modifyInfo(InfoEntity infoEntity);

	void addWeight(InfoEntity infoEntity);

	void addPageView(Long id);

	// add by fjx begin
	List<InfoEntity> searUncheckchInfos();

	String getCheckResult(long id);
	// add nu fjx end
}
