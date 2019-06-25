package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyPrizeRecordRepository;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;

@Service(value = "prizeRecordServiceImpl")
@Transactional(readOnly = true)
public class PrizeRecordServiceImpl implements PrizeRecordService{
	@Autowired
	private MyPrizeRecordRepository myPrizeRecordRepository;
	@Override
	public List<PrizeRecordEntity> getAllPrizeRecord(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByOrderById(pageRequest);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myPrizeRecordRepository.count();
	}
}
