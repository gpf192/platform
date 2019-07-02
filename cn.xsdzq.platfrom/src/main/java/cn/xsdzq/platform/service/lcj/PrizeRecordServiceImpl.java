package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyPrizeRecordRepository;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
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
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(
			Date beginDate, Date endDate, String prizeName, String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(beginDate, endDate, prizeName, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccount(Date beginDate, Date endDate, String prizeName, String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameAndAccount(beginDate, endDate, prizeName, account);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualOrderByCreatetime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualOrderByCreatetime(beginDate, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeLessThanEqualOrderByCreatetime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeLessThanEqualOrderByCreatetime(endDate, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeLessThanEqual(endDate);
	}
	@Override
	public List<PrizeRecordEntity> findByPrizeNameOrderByCreatetime(String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByPrizeNameOrderByCreatetime(prizeName, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeName(String prizeName) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeName(prizeName);
	}
	@Override
	public List<PrizeRecordEntity> findByAccountOrderByCreatetime(String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByAccountOrderByCreatetime(account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByAccount(String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByAccount(account);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualOrderByCreatetime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualOrderByCreatetime(beginDate, endDate, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameOrderByCreatetime(Date beginDate,
			String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndPrizeNameOrderByCreatetime(beginDate, prizeName, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndPrizeName(Date beginDate, String prizeName) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndPrizeName(beginDate, prizeName);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndAccountOrderByCreatetime(Date beginDate,
			String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndAccountOrderByCreatetime(beginDate, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndAccount(Date beginDate, String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndAccount(beginDate, account);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(Date endDate,
			String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(endDate, prizeName, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeLessThanEqualAndPrizeName(Date endDate, String prizeName) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeLessThanEqualAndPrizeName(endDate, prizeName);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndAccountOrderByCreatetime(Date endDate,
			String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeLessThanEqualAndAccountOrderByCreatetime(endDate, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeLessThanEqualAndAccount(Date endDate, String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeLessThanEqualAndAccount(endDate, account);
	}
	@Override
	public List<PrizeRecordEntity> findByPrizeNameAndAccountOrderByCreatetime(String prizeName, String account,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByPrizeNameAndAccountOrderByCreatetime(prizeName, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByPrizeNameAndAccount(String prizeName, String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByPrizeNameAndAccount(prizeName, account);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(
			Date beginDate, Date endDate, String prizeName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeNameOrderByCreatetime(beginDate,endDate, prizeName, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeName(Date beginDate, Date endDate,
			String prizeName) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndCreatetimeLessThanEqualAndPrizeName(beginDate,endDate, prizeName);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeGreaterThanEqualAndPrizeNameAndAccountOrderByCreatetime(
			Date beginDate, String prizeName, String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeGreaterThanEqualAndPrizeNameAndAccountOrderByCreatetime(beginDate, prizeName, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeGreaterThanEqualAndPrizeNameAndAccount(Date beginDate, String prizeName,
			String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeGreaterThanEqualAndPrizeNameAndAccount(beginDate, prizeName, account);
	}
	@Override
	public List<PrizeRecordEntity> findByCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(Date endDate,
			String prizeName, String account, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<PrizeRecordEntity> pages = myPrizeRecordRepository.findByCreatetimeLessThanEqualAndPrizeNameAndAccountOrderByCreatetime(endDate, prizeName, account, pageable);
		List<PrizeRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByCreatetimeLessThanEqualAndPrizeNameAndAccount(Date endDate, String prizeName, String account) {
		// TODO Auto-generated method stub
		return myPrizeRecordRepository.countByCreatetimeLessThanEqualAndPrizeNameAndAccount(endDate, prizeName, account);
	}
}
