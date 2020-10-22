package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PageCrmCreditApiErrMsgRepository;
import cn.xsdzq.platform.dao.mall.PageCrmCreditRecordRepository;
import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;
import cn.xsdzq.platform.service.mall.CRMCreditRecordService;
@Service(value = "crmCreditRecordServiceImpl")
@Transactional(readOnly = true)
public class CRMCreditRecordServiceImpl implements CRMCreditRecordService {
	@Autowired
	private PageCrmCreditRecordRepository pageCrmCreditRecordRepository;
	
	@Autowired
	private PageCrmCreditApiErrMsgRepository pageCrmCreditApiErrMsgRepository;

	@Override
	public List<CRMCreditRecordEntity> findByOrderByBeginDateDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByOrderByBeginDateDesc(pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pageCrmCreditRecordRepository.count();
	}	
	
	@Override
	public List<CRMCreditRecordEntity> findByClientIdLikeOrderByBeginDateDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByClientIdLikeOrderByBeginDateDesc(clientId,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByClientIdLike(clientId);
	}

	@Override
	public List<CRMCreditRecordEntity> findBySerialNumLikeOrderByBeginDateDesc(String serialNum, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findBySerialNumLikeOrderByBeginDateDesc(serialNum,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countBySerialNumLike(String serialNum) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countBySerialNumLike(serialNum);
	}

	@Override
	public List<CRMCreditRecordEntity> findByItemCodeLikeOrderByBeginDateDesc(String serialNum, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByItemCodeLikeOrderByBeginDateDesc(serialNum,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByItemCodeLike(String serialNum) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByItemCodeLike(serialNum);
	}
	
	@Override
	public List<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeOrderByBeginDateDesc(String clientId,
			String serialNum, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByClientIdLikeAndSerialNumLikeOrderByBeginDateDesc(clientId,serialNum,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdLikeAndSerialNumLike(String clientId, String serialNum) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByClientIdLikeAndSerialNumLike(clientId, serialNum);
	}
	
	//报错信息查询
	@Override
	public List<CRMCreditApiErrorMsgEntity> findMsgByOrderByRecordTimeDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditApiErrorMsgEntity> pages = pageCrmCreditApiErrMsgRepository.findByOrderByRecordTimeDesc(pageRequest);		
		List<CRMCreditApiErrorMsgEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countMsgAll() {
		// TODO Auto-generated method stub
		return (int) pageCrmCreditApiErrMsgRepository.count();
	}
	@Override
	public List<CRMCreditApiErrorMsgEntity> findMsgBySerialNumLikeOrderByRecordTimeDesc(String serialNum,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditApiErrorMsgEntity> pages = pageCrmCreditApiErrMsgRepository.findBySerialNumLikeOrderByRecordTimeDesc(serialNum, pageRequest);		
		List<CRMCreditApiErrorMsgEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countMsgBySerialNumLike(String serialNum) {
		// TODO Auto-generated method stub
		return pageCrmCreditApiErrMsgRepository.countBySerialNumLike(serialNum);
	}
	@Override
	public List<CRMCreditRecordEntity> findByClientIdLikeAndItemCodeLikeOrderByBeginDateDesc(String clientId,
			String itemCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByClientIdLikeAndItemCodeLikeOrderByBeginDateDesc(clientId,itemCode,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientIdLikeAndItemCodeLike(String clientId, String itemCode) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByClientIdLikeAndItemCodeLike(clientId, itemCode);
	}
	@Override
	public List<CRMCreditRecordEntity> findByItemCodeLikeAndSerialNumLikeOrderByBeginDateDesc(String itemCode,
			String serialNum, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByItemCodeLikeAndSerialNumLikeOrderByBeginDateDesc(itemCode,serialNum,pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByItemCodeLikeAndSerialNumLike(String itemCode, String serialNum) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByItemCodeLikeAndSerialNumLike(itemCode, serialNum);
	}
	@Override
	public List<CRMCreditRecordEntity> findByClientIdLikeAndSerialNumLikeAndItemCodeLikeOrderByBeginDateDesc(
			String clientId, String serialNum, String itemCode, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<CRMCreditRecordEntity> pages = pageCrmCreditRecordRepository.findByClientIdLikeAndSerialNumLikeAndItemCodeLikeOrderByBeginDateDesc(clientId, serialNum, itemCode, pageRequest);		
		List<CRMCreditRecordEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByClientIdLikeAndSerialNumLikeAndItemCodeLike(String clientId, String serialNum, String itemCode) {
		// TODO Auto-generated method stub
		return pageCrmCreditRecordRepository.countByClientIdLikeAndSerialNumLikeAndItemCodeLike(clientId, serialNum, itemCode);
	}
	
	
	
}
