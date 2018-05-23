package cn.xsdzq.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.RecordRepository;
import cn.xsdzq.platform.entity.RecordEntity;

@Service(value = "recordServiceImpl")
@Transactional(readOnly = true)
public class RecordServiceImpl implements IRecordService {

	@Autowired
	private RecordRepository recordRepository;

	@Override
	@Transactional
	public void addRecord(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		recordRepository.addRecord(recordEntity);
	}

	@Override
	@Transactional
	public void mergeRecord(RecordEntity recordEntity) {
		// TODO Auto-generated method stub
		recordRepository.mergeRecord(recordEntity);

	}

}
