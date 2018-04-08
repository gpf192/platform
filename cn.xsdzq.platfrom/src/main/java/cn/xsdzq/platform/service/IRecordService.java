package cn.xsdzq.platform.service;

import cn.xsdzq.platform.entity.RecordEntity;

public interface IRecordService {

	public void addRecord(RecordEntity recordEntity);

	public void mergeRecord(RecordEntity recordEntity);

}
