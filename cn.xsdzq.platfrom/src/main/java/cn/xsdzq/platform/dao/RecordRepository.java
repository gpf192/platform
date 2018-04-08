package cn.xsdzq.platform.dao;

import cn.xsdzq.platform.entity.RecordEntity;

public interface RecordRepository {

	public void addRecord(RecordEntity recordEntity);

	public void mergeRecord(RecordEntity recordEntity);

}
