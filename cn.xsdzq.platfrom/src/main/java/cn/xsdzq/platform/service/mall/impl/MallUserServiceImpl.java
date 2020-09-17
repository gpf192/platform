package cn.xsdzq.platform.service.mall.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditCategoryRepository;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.MallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.MallUserRepository;
import cn.xsdzq.platform.dao.mall.PageMallUserInfoRepository;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;



@Service(value = "mallUserServiceImpl")
@Transactional(readOnly = true)
public class MallUserServiceImpl implements MallUserService {
	private static final Logger log = LoggerFactory.getLogger(MallUserServiceImpl.class);

	@Autowired
	private MallUserRepository mallUserRepository;

	@Autowired
	private MallUserInfoRepository mallUserInfoRepository;

	@Autowired
	private CreditRecordRepository creditRecordRepository;
	
	@Autowired
	private PageMallUserInfoRepository pageMallUserInfoRepository;
	
	@Autowired
	private CreditCategoryRepository creditCategoryRepository;
	
	@Override
	public void addMallUser(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@Transactional
	public void addCreditScore(CreditImportTempEntity temp) {
		// TODO Auto-generated method stub	
		int tempNum = Integer.parseInt(temp.getNum());//分数String转化为整数
		//临时表导入的数据作为参数
		
		// 2.用户的查找和新增
		MallUserEntity owner = mallUserRepository.findByClientId(temp.getClientId());
		if (owner == null) {
			owner = new MallUserEntity();
			owner.setClientId(temp.getClientId());
			owner.setClientName(temp.getClientName());
			owner.setMobile(temp.getMobile());
			owner.setDepartmentCode(temp.getDepartmentCode());
			owner.setDepartmentName(temp.getDepartmentDesc());
			mallUserRepository.save(owner);

			MallUserInfoEntity mallUserInfoEntity = new MallUserInfoEntity();
			mallUserInfoEntity.setMallUserEntity(owner);
			mallUserInfoEntity.setCreditScore(0);//当前总积分 ，有加有减
			mallUserInfoEntity.setSumScore(0);//历史总积分 ，只加不减
			mallUserInfoEntity.setUserLevel(0);
			mallUserInfoEntity.setCreatetime(new Date());
			mallUserInfoRepository.save(mallUserInfoEntity);
		} else {
			// update 逻辑 把部门信息同步过去
			owner.setDepartmentCode(temp.getDepartmentCode());
			owner.setDepartmentName(temp.getDepartmentDesc());
			mallUserRepository.save(owner);//此处测试是否更新
		}
		// 3.项目的类别，查询是否存在项目code，不存在则新建
		CreditEntity creditEntity = creditCategoryRepository.findByCategoryCode(temp.getCategoryCode());
		if (creditEntity == null){
			creditEntity = new CreditEntity();
			creditEntity.setCategoryCode(temp.getCategoryCode());
			creditEntity.setCategoryName(temp.getCategoryName());
			creditEntity.setIntegralValue(temp.getNum());
			creditEntity.setFlag("1");
			creditEntity.setCreatetime(new Date());
			
			creditCategoryRepository.save(creditEntity);
		}
		// 4.导入附件记录
		//此处只写增加记录
		String nowFlag = DateUtil.getStandardDate(new Date());
		CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
		creditRecordEntity.setMallUserEntity(owner);
		creditRecordEntity.setType(true);//true 增加，  false  减少
		creditRecordEntity.setItem(temp.getCategoryName());
		creditRecordEntity.setItemCode(temp.getCategoryCode());
		//creditRecordEntity.setReason("兑换奖品");
		//creditRecordEntity.setReasonCode("10");
		creditRecordEntity.setIntegralNumber(tempNum);//导入积分
		creditRecordEntity.setDateFlag(nowFlag);
		creditRecordEntity.setBeginDate(temp.getBeginDate());
		creditRecordEntity.setEndDate(temp.getEndDate());
		creditRecordEntity.setGroupTime(DateUtil.Dateymd(new Date()));//20200810
		creditRecordEntity.setRecordTime(new Date());
		creditRecordRepository.save(creditRecordEntity);

		// 5.个人信息累计总积分值

		MallUserInfoEntity myMallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(owner);
		//判断当前导入的 temp    分数失效日期是否小于当天， 小于当天 不加入
		if(Integer.parseInt(temp.getEndDate()) >= DateUtil.DateToStringAsDayWithoutLine(new Date())) {
			myMallUserInfoEntity.setCreditScore(tempNum + myMallUserInfoEntity.getCreditScore());
			myMallUserInfoEntity.setSumScore(tempNum + myMallUserInfoEntity.getSumScore());
			myMallUserInfoEntity.setModifytime(new Date());
		}		
		mallUserInfoRepository.save(myMallUserInfoEntity);

	}
	
	@Override
	public MallUserEntity getUserByClientId(String clientId) {
		// TODO Auto-generated method stub
		MallUserEntity mallUserEntity = mallUserRepository.findByClientId(clientId);
		return mallUserEntity;
	}
	//分页查询
	@Override
	public List<MallUserInfoEntity> findByOrderByCreditScoreDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByOrderByCreditScoreDesc(pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pageMallUserInfoRepository.count();
	}
//
	@Override
	public List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeOrderByCreditScoreDesc(String username, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByMallUserEntity_clientNameLikeOrderByCreditScoreDesc(username, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientNameLike(String username) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByMallUserEntity_clientNameLike(username);
	}

	@Override
	public List<MallUserInfoEntity> findByClientIdLikeOrderByCreditScoreDesc(String clientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByClientIdLikeOrderByCreditScoreDesc(clientId, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdLike(String clientId) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByClientIdLike(clientId);

	}

	@Override
	public List<MallUserInfoEntity> findByMallUserEntity_moblieLikeOrderByCreditScoreDesc(String moblie, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByMallUserEntity_mobileLikeOrderByCreditScoreDesc(moblie, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_moblieLike(String moblie) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByMallUserEntity_mobileLike(moblie);

	}
//
	@Override
	public List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClentIdLikeOrderByCreditScoreDesc(
			String username, String clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByMallUserEntity_clientNameLikeAndClientIdLikeOrderByCreditScoreDesc( username,  clientId, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientNameLikeAndClentIdLike(String username, String clientId) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByMallUserEntity_clientNameLikeAndClientIdLike(username, clientId);
	}

	@Override
	public List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(
			String username, String moblie, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByMallUserEntity_clientNameLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc( username,  moblie, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientNameLikeAndMallUserEntity_moblieLike(String username, String moblie) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByMallUserEntity_clientNameLikeAndMallUserEntity_mobileLike(username, moblie);

	}

	@Override
	public List<MallUserInfoEntity> findByClientIdLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(
			String clientId, String moblie, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByClientIdLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc( clientId,  moblie, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByClientIdLikeAndMallUserEntity_moblieLike(String clientId, String moblie) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByClientIdLikeAndMallUserEntity_mobileLike(clientId, moblie);

	}
//
	@Override
	public List<MallUserInfoEntity> findByMallUserEntity_clientNameLikeAndClentIdLikeAndMallUserEntity_moblieLikeOrderByCreditScoreDesc(
			String username, String clientId, String moblie, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<MallUserInfoEntity> pages = pageMallUserInfoRepository.findByMallUserEntity_clientNameLikeAndClientIdLikeAndMallUserEntity_mobileLikeOrderByCreditScoreDesc( username,  clientId, moblie, pageRequest);
			
		List<MallUserInfoEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByMallUserEntity_clientNameLikeAndClentIdLikeAndMallUserEntity_moblieLike(String username,
			String clientId, String moblie) {
		// TODO Auto-generated method stub
		return pageMallUserInfoRepository.countByMallUserEntity_clientNameLikeAndClientIdLikeAndMallUserEntity_mobileLike(username, clientId, moblie);

	}



	
}
