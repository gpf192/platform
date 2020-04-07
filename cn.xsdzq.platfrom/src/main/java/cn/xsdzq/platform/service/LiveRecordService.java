package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.LiveRecordEntity;

public interface LiveRecordService {
	int countAll();	
	List<LiveRecordEntity> getAllLiveRecord(int pageNumber, int pageSize);
	
	List<LiveRecordEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(String ClientId, int pageNumber, int pageSize);
	
	int countByUserEntity_clientId(String ClientId);
}
