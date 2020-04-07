package cn.xsdzq.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.LiveRecordRepository;
import cn.xsdzq.platform.entity.LiveRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;

@Service(value = "liveRecordServiceImpl")
@Transactional(readOnly = true)
public class LiveRecordServiceImpl implements LiveRecordService{
	@Autowired
	private LiveRecordRepository liveRecordRepository;

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)liveRecordRepository.count();
	}

	@Override
	public List<LiveRecordEntity> getAllLiveRecord(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<LiveRecordEntity> pages = liveRecordRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<LiveRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public List<LiveRecordEntity> findByUserEntity_clientIdOrderByRecordTimeDesc(
			String ClientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<LiveRecordEntity> pages = liveRecordRepository.findByUserEntity_clientIdOrderByRecordTimeDesc(ClientId, pageable);
		List<LiveRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientId(String ClientId) {
		// TODO Auto-generated method stub
		return liveRecordRepository.countByUserEntity_clientId(ClientId);
	}
}
