package cn.xsdzq.platform.service.mall.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.xsdzq.platform.constants.CreditRecordConst;
import cn.xsdzq.platform.dao.lcj.ParamRepository;
import cn.xsdzq.platform.dao.mall.CreditCategoryRepository;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.MailItemListRepository;
import cn.xsdzq.platform.dao.mall.MallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.MallUserRepository;
import cn.xsdzq.platform.dao.mall.PageCrmCreditApiErrMsgRepository;
import cn.xsdzq.platform.dao.mall.PageCrmCreditRecordRepository;
import cn.xsdzq.platform.dao.mall.PageMallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.PresentCardRepository;
import cn.xsdzq.platform.dao.mall.PresentRepository;
import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MailItemListEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.mall.UserIntegralDTO;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.SendEmailUtil;
import cn.xsdzq.platform.util.UserManageUtil;



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
	
	
	@Autowired
	private PageCrmCreditRecordRepository pageCrmCreditRecordRepository;
	
	@Autowired
	private PageCrmCreditApiErrMsgRepository pageCrmCreditApiErrMsgRepository;
	
	@Autowired
	private PresentCardRepository presentCardRepository;
	
	@Autowired
	private PresentRepository presentRepository;
	
	@Autowired
	private ParamRepository paramRepository;
	 
	@Autowired
	private MailItemListRepository mailItemListRepository;
	
	@Override
	public void addMallUser(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@Transactional
	public boolean modifyUserTotalIntegral(UserIntegralDTO dto) {
		//判断剩余积分是否足够扣减
		MallUserInfoEntity info = mallUserInfoRepository.findByClientId(dto.getClientId());
		if("0".equals(dto.getFlag())) {
			int num = info.getCreditScore() - dto.getChangeNum();
			if(num < 0) {
				return false;
			}
		}
		//如果增加积分， 按照导入积分逻辑，生成相应的截止日期
		
		//如果减少积分，就走兑换逻辑进行扣减
		MallUserEntity user =  mallUserRepository.findByClientId(dto.getClientId());
		CreditRecordEntity  record = new CreditRecordEntity();
		record.setSerialNum("manual");		
		record.setClientId(dto.getClientId());
		record.setIntegralNumber(dto.getChangeNum());
		record.setDateFlag(DateUtil.getStandardDate(new Date()));//2021-01-02
		record.setGroupTime(DateUtil.getStandardDate(new Date()).substring(0, 7));
		record.setBeginDate(DateUtil.Dateymd(new Date()));
		record.setRecordTime(new Date());
		record.setMallUserEntity(user);
		//写入前端显示名称
		CreditEntity centity = creditCategoryRepository.findByCategoryCode(dto.getCategoryCode());
		record.setItem(centity.getFrontName());
		record.setItemCode(dto.getCategoryCode());
		if("1".equals(dto.getFlag())) {
			ParamEntity p =paramRepository.getValueByCode("cvp");
			int cvp = Integer.parseInt(p.getValue());//当前有效期天数
			info.setCreditScore(info.getCreditScore() + dto.getChangeNum());
			record.setType(true);
			record.setImportItem(dto.getCategoryName());
			record.setEndDate(DateUtil.getFutureDayAsInt(DateUtil.Dateymd(new Date()),cvp-1));//

			
		}else {
			info.setCreditScore(info.getCreditScore() - dto.getChangeNum());
			record.setType(false);
			record.setReason("积分变更");
			record.setReasonCode(dto.getCategoryCode());
			//走正常兑换消耗积分处理逻辑
			try {
				handleRudeceCredit( user,  dto.getChangeNum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mallUserInfoRepository.save(info);
		creditRecordRepository.save(record);
		return true;
	}
	//扣减积分


	void handleRudeceCredit(MallUserEntity mallUserEntity, int reduceScore) {
		List<CreditRecordEntity> creditRecordEntities = creditRecordRepository.findByUnusedCredit(mallUserEntity,
				CreditRecordConst.ADDSCORE, 1);
		log.info("creditRecordEntities: " + creditRecordEntities.size());
		for (CreditRecordEntity creditRecordEntity : creditRecordEntities) {
			log.info(creditRecordEntity.toString());
			// 做减法
			int score;
			if (creditRecordEntity.getChangeType() == CreditRecordConst.CHANGETYPE_UNUSED) {
				score = creditRecordEntity.getIntegralNumber();
			} else {
				score = creditRecordEntity.getRemindNumer();
			}
			if (reduceScore - score > 0) {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
				creditRecordEntity.setRemindNumer(0);
				reduceScore = reduceScore - score;
				creditRecordRepository.save(creditRecordEntity);
			} else if (reduceScore - score == 0) {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_COMPLETE);
				creditRecordEntity.setRemindNumer(0);
				reduceScore = reduceScore - score;
				creditRecordRepository.save(creditRecordEntity);
				break;
			} else {
				creditRecordEntity.setChangeType(CreditRecordConst.CHANGETYPE_REMIND);
				creditRecordEntity.setRemindNumer(score - reduceScore);
				reduceScore = 0;
				creditRecordRepository.save(creditRecordEntity);
				break;
			}
		}

	}

	@Override
	@Transactional
	public void addCreditScore(CreditImportTempEntity temp) {
		// TODO Auto-generated method stub	
		ParamEntity p =paramRepository.getValueByCode("cvp");
		int cvp = Integer.parseInt(p.getValue());//当前有效期天数
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
		String tempName = "";
		if (creditEntity == null){
			creditEntity = new CreditEntity();
			creditEntity.setCategoryCode(temp.getCategoryCode());
			creditEntity.setCategoryName(temp.getCategoryName());
			creditEntity.setFrontName(temp.getCategoryName());
			creditEntity.setIntegralValue(temp.getNum());
			creditEntity.setFlag("1");
			creditEntity.setCreatetime(new Date());
			
			creditCategoryRepository.save(creditEntity);
		}else{
			tempName =creditEntity.getFrontName() ;
		}
		// 4.导入附件记录
		//此处只写增加记录
		String nowFlag = DateUtil.getStandardDate(new Date());
		CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
		creditRecordEntity.setMallUserEntity(owner);
		creditRecordEntity.setType(true);//true 增加，  false  减少
		creditRecordEntity.setImportItem(temp.getCategoryName());
		//取项目分类的前端显示字段
		if("".equals(tempName)) {
			creditRecordEntity.setItem(temp.getCategoryName());
		}else {
			creditRecordEntity.setItem(tempName);
		}	
		creditRecordEntity.setItemCode(temp.getCategoryCode());
		creditRecordEntity.setIntegralNumber(tempNum);//导入积分
		creditRecordEntity.setDateFlag(nowFlag);
		creditRecordEntity.setBeginDate(temp.getBeginDate());
		creditRecordEntity.setEndDate(DateUtil.getFutureDayAsInt(temp.getBeginDate(),cvp-1));//
		creditRecordEntity.setGroupTime(DateUtil.Dateym(temp.getBeginDate()));//20200810
		creditRecordEntity.setRecordTime(new Date());
		creditRecordEntity.setImportMobile(temp.getMobile());
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

	@Override
	public MallUserInfoEntity findByClientId(String clientId) {
		// TODO Auto-generated method stub
		return mallUserInfoRepository.findByClientId(clientId);
	}
//扫描失效积分
	@Override
	@Transactional
	public void endDateJob() {
		int preday =DateUtil.getPreDayAsInt(1);
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		//筛选条件 :结束日期，type为1表示增加的记录，changetype为0或1，标识增加的未兑换或未完全兑换
		List<CreditRecordEntity> recordList = creditRecordRepository.findByEndDateAndTypeAndChangeTypeIn(preday,  true, list);
		System.out.println("昨天共有几条到期记录 -- "+recordList.size());
		//，0，新增状态，未兑换，1-未完全兑换，2-已完成兑换，3-已过期
		/**
		 *  cahngeType 为 0 ， 取integralNumber ，最后 type置位3；
			cahngeType 为 1 ，取remindNumer ， 最后type置位3  remindnumber置位 0
			cahngeType 为 2， 忽略
		 */
		for(CreditRecordEntity record:recordList) {	
			if(record.getChangeType() == 0) {
				String clientId = record.getClientId();
				MallUserInfoEntity userInfo = mallUserInfoRepository.findByClientId(clientId);
				CreditRecordEntity c = new CreditRecordEntity();
				c.setClientId(clientId);
				c.setSerialNum(record.getSerialNum());
				c.setDateFlag(DateUtil.getPreDayAsString());//2020-10-01
				c.setGroupTime(DateUtil.Dateym(String.valueOf(preday)));//2020-10
				c.setIntegralNumber(record.getIntegralNumber());//失效分数
				c.setItem(record.getItem());
				c.setItemCode(record.getItemCode());
				MallUserEntity mallUser = mallUserRepository.findByClientId(clientId);//必须保持clientid 唯一
				//处理  用户总积分表中有相同clientId的bug
				c.setMallUserEntity(mallUser);
				c.setReason(CreditRecordConst.EXPIREDCARDREASON);
				c.setReasonCode(CreditRecordConst.EXPIREDCARD);//固定常亮
				c.setRecordTime(new Date());
				c.setType(false);//false-消耗 或兑换，true-增加
				System.out.println("插入一条失效记录 "+c.toString());
				creditRecordRepository.save(c);
				record.setChangeType(CreditRecordConst.CHANGETYPE_EXPRIE);//置位失效状态  
				creditRecordRepository.save(record);//增加失效记录
				int tempTotal = userInfo.getCreditScore()-record.getIntegralNumber();
				userInfo.setCreditScore(tempTotal);
				mallUserInfoRepository.save(userInfo);//更改总积分
			}
			if(record.getChangeType() == 1) {
				String clientId = record.getClientId();
				int tempNum = record.getRemindNumer();
				MallUserInfoEntity userInfo = mallUserInfoRepository.findByClientId(clientId);
				CreditRecordEntity c = new CreditRecordEntity();
				c.setClientId(clientId);
				c.setSerialNum(record.getSerialNum());
				c.setDateFlag(DateUtil.getPreDayAsString());//2020-10-01
				c.setGroupTime(DateUtil.Dateym(String.valueOf(preday)));//2020-10
				c.setIntegralNumber(tempNum);//失效分数，取剩余分数
				c.setItem(record.getItem());
				c.setItemCode(record.getItemCode());
				MallUserEntity mallUser = mallUserRepository.findByClientId(clientId);//必须保持clientid 唯一
				//处理  用户总积分表中有相同clientId的bug
				c.setMallUserEntity(mallUser);
				c.setReason(CreditRecordConst.EXPIREDCARDREASON);//已失效
				c.setReasonCode(CreditRecordConst.EXPIREDCARD);//11
				c.setRecordTime(new Date());
				c.setType(false);//false-消耗 或兑换，true-增加
				System.out.println("插入一条失效记录 "+c.toString());
				creditRecordRepository.save(c);
				record.setChangeType(CreditRecordConst.CHANGETYPE_EXPRIE);//置位失效状态
				record.setRemindNumer(0);//剩余分数置位0
				creditRecordRepository.save(record);//增加失效记录
				int tempTotal = userInfo.getCreditScore()-tempNum;
				userInfo.setCreditScore(tempTotal);
				mallUserInfoRepository.save(userInfo);//更改总积分
			}
		}
		
		
	}
	//失效卡券
	@Override
	@Transactional
	public void cardEndDateJob() {
		System.out.println(" 开始扫描失效卡券 "+new Date());
		List<PresentCardEntity> carList = new ArrayList<PresentCardEntity>();
		/**
		 * 查询条件：当天日期（int类型），未兑换（值是0）
		 */
		carList = presentCardRepository.findByExpiryTimeAndConvertStatus(DateUtil.getNowDate(), 0);
		if(carList.size() == 0) {
			System.out.println(" 今日无失效卡券 "+new Date());
		}else {
			System.out.println(" 今日失效卡券 "+carList.size()+" 条 "+new Date());
			for(PresentCardEntity car:carList) {
				car.setCardStatus(0);//卡券状态， 上架1/下架0
				presentCardRepository.save(car);
				//所属产品类别库存总数 -1
				PresentEntity present = car.getPresent();
				present.setStoreUnused(present.getStoreUnused()-1);
				presentRepository.save(present);
			}
		}
		
	}
	//@Override  此算法冗余，弃用
	/*@Transactional
	public void endDateJobBak() {
		int preday =DateUtil.getPreDayAsInt(1);
		List<CreditRecordEntity> recordList = creditRecordRepository.findByEndDateAndType(preday,  true);
		System.out.println("昨天共有几条到期记录 -- "+recordList.size());
		for(CreditRecordEntity record:recordList) {	
			String clientId = record.getClientId();
			MallUserInfoEntity userInfo = mallUserInfoRepository.findByClientId(clientId);
			//只筛选当前积分大于0的，  等于0的已经消耗完毕 不用关注失效分数
			//需要插入的失效record = t-1及之前 共失效 - t-1之前，对换+失效记录
			if(userInfo.getCreditScore() > 0) {
				System.out.println("clientId "+clientId+" 总积分大于0 ，进入循环 ");
				int sum1 = 0;
				int sum2 = 0;
				int tempNum = 0;//记录里失效分数
				int sum3 = 0;
				int sum4 = 0;
				int tempTotal = 0;//存储计算的总积分
				List<CreditRecordEntity> list1 = creditRecordRepository.findByClientIdAndEndDateLessThanEqualAndType(clientId,preday,  true);
				for(CreditRecordEntity r1:list1) {
					sum1 = sum1 + r1.getIntegralNumber();
				}
				List<CreditRecordEntity> list2 = creditRecordRepository.findByClientIdAndEndDateLessThanEqualAndType(clientId, preday,  false);
				for(CreditRecordEntity r2:list2) {
					sum2 = sum2 + r2.getIntegralNumber();
				}
				System.out.println("clientI "+clientId+",sum1: "+sum1+", sum2: "+sum2);
				
				
				if(sum1 > sum2) {
					tempNum = sum1 -sum2;
					System.out.println("sum1>sum2, clientI "+clientId+", sum1: "+sum1+", sum2: "+sum2+",sum1-sum2= tempNum: "+tempNum);
					CreditRecordEntity c = new CreditRecordEntity();
					c.setClientId(clientId);
					c.setDateFlag(DateUtil.getPreDayAsString());//2020-10-01
					c.setGroupTime(DateUtil.Dateym(String.valueOf(preday)));//2020-10
					c.setIntegralNumber(tempNum);//失效分数
					c.setItem(record.getItem());
					c.setItemCode(record.getItemCode());
					MallUserEntity mallUser = mallUserRepository.findByClientId(clientId);//必须保持clientid 唯一
					//处理  用户总积分表中有相同clientId的bug
					c.setMallUserEntity(mallUser);
					c.setReason("失效");
					c.setReasonCode("11");//固定常亮
					c.setRecordTime(new Date());
					c.setType(false);
					System.out.println("插入一条失效记录 "+c.toString());
					creditRecordRepository.save(c);
					//增加失效记录的同时，更新当前剩余积分
					//当前总得分 - t-1日及之前总兑换+失效）

					List<CreditRecordEntity> list3 = creditRecordRepository.findByClientIdAndType(clientId,  true);
					for(CreditRecordEntity r3:list3) {
						sum3 = sum3 + r3.getIntegralNumber();
					}	
					
					List<CreditRecordEntity> list4 = creditRecordRepository.findByClientIdAndEndDateLessThanEqualAndType(clientId, preday,  false);
					for(CreditRecordEntity r4:list4) {
						sum4 = sum4 + r4.getIntegralNumber();
					}
					System.out.println("clientI "+clientId+",sum3: "+sum3+", sum4: "+sum4);
					if(sum3 > sum4) {
						tempTotal = sum3 - sum4;
						System.out.println("开始计算总积分，clientI "+clientId+",sum3: "+sum3+", sum4: "+sum4+", sum3-sum4=tempTotal: "+tempTotal);
						userInfo.setCreditScore(tempTotal);
						mallUserInfoRepository.save(userInfo);
					}
				}
				
			}
			
			
		}
	}*/

	
	
	@Override
	@Transactional
	public void scanCrmCreditJob() {
		// TODO Auto-generated method stub
		//扫描crmrecord 前一天记录
		 int preday =DateUtil.getPreDayAsInt(10); 
		// 临时同步数据
		//int preday =20210530; 
		List<CRMCreditRecordEntity> crmlist = new ArrayList<CRMCreditRecordEntity>();
		crmlist = pageCrmCreditRecordRepository.findByBeginDateGreaterThan(preday);
		
		System.out.println("crm 有几条记录"+crmlist.size());
		ParamEntity p =paramRepository.getValueByCode("cvp");
		int cvp = Integer.parseInt(p.getValue());//当前有效期天数
		
		//循环，校验数据，不合格的 插入error表,合格的插入record表，
		for(CRMCreditRecordEntity entity:crmlist) {
			List<CreditRecordEntity> l = creditRecordRepository.findBySerialNum(entity.getSerialNum());	
			if(l.size() == 0) {
			//记录表不存在，则插入
			int flag = 0;
			String msg ="";
			int endDate = DateUtil.getFutureDayAsInt(String.valueOf(entity.getBeginDate()),cvp-1);
			if( entity.getClientName() == null|| entity.getDepartmentCode() == null|| entity.getDepartmentDesc()== null|| 
					entity.getItemName() == null|| entity.getItemCode() == null || String.valueOf(entity.getNum())== null || String.valueOf(entity.getBeginDate())== null ) {
				flag=+1;
				msg =msg+"存在空字段；";
			}
			
			if(String.valueOf(entity.getBeginDate()).length() != 8 ) {
				flag=+1;
				msg =msg+"日期格式错误；";
			}
			if(endDate <= DateUtil.getPreDayAsInt(1)) {
				flag=+1;
				msg =msg+"积分截止日期小于当前日期，请调整积分有效期参数；";
			}	
			//判断 生效日期不能大于当天
			if(endDate < entity.getBeginDate() ) {
				flag=+1;
				msg =msg+"积分截止日期小于生效日期；";
			}
			if(entity.getBeginDate()>Integer.parseInt(DateUtil.Dateymd(new Date()))) {
				flag=+1;
				msg =msg+"生效日期大于当前日期；";//不会扫到，只会扫描t-1
			}
			if( entity.getNum() <= 0) {
				flag=+1;
				msg =msg+"分数为非正数；";
			}
			if(flag > 0) {
				System.out.println("crm 校验数据，发现错误");
				//插入错误表
				CRMCreditApiErrorMsgEntity errorEntity = new CRMCreditApiErrorMsgEntity();
				errorEntity.setMsg(msg);
				errorEntity.setSerialNum(entity.getSerialNum());
				errorEntity.setRecordTime(new Date());
				errorEntity.setSta(0);
				pageCrmCreditApiErrMsgRepository.save(errorEntity);
			}else {
				//接口数据转化为正式数据
				
				try {
					changeToCreditRecord(entity);
					System.out.println("crm 校验数据完毕，转换正式数据完毕");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("crm 校验数据完毕，转换过程未知异常");
					CRMCreditApiErrorMsgEntity errorEntity = new CRMCreditApiErrorMsgEntity();
					errorEntity.setMsg("转换过程未知异常");
					errorEntity.setSerialNum(entity.getSerialNum());
					errorEntity.setRecordTime(new Date());
					errorEntity.setSta(0);
					pageCrmCreditApiErrMsgRepository.save(errorEntity);
				}
			}
		}
	}
		
		
	}
	//接口数据转化为正式数据
	public void changeToCreditRecord(CRMCreditRecordEntity temp) {
		MallUserEntity owner = mallUserRepository.findByClientId(temp.getClientId());
		ParamEntity p =paramRepository.getValueByCode("cvp");
		int cvp = Integer.parseInt(p.getValue());//当前有效期天数
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
			//将用户姓名再次更新，供前端安全校验
			 if(temp.getClientName() !=null && temp.getClientName().length() >1){
				 owner.setClientName(temp.getClientName());
				 }
			// update 逻辑 把部门信息同步过去
			owner.setDepartmentCode(temp.getDepartmentCode());
			owner.setDepartmentName(temp.getDepartmentDesc());
			mallUserRepository.save(owner);//此处测试是否更新
		}
		// 3.项目的类别，查询是否存在项目code，不存在则新建
		CreditEntity creditEntity = creditCategoryRepository.findByCategoryCode(temp.getItemCode());
		String tempName = "";
		if (creditEntity == null){
			creditEntity = new CreditEntity();
			creditEntity.setCategoryCode(temp.getItemCode());
			creditEntity.setCategoryName(temp.getItemName());
			creditEntity.setFrontName(temp.getItemName());
			creditEntity.setFlag("1");
			creditEntity.setCreatetime(new Date());
			
			creditCategoryRepository.save(creditEntity);
		}else{
			tempName =creditEntity.getFrontName() ;
		}
		// 4.导入记录
		//此处只写增加记录
		String nowFlag = DateUtil.getStandardDate(new Date());
		CreditRecordEntity creditRecordEntity = new CreditRecordEntity();
		creditRecordEntity.setMallUserEntity(owner);
		creditRecordEntity.setType(true);//true 增加，  false  减少
		creditRecordEntity.setImportItem(temp.getItemName());
		//取项目分类的前端显示字段
		if("".equals(tempName)) {
			creditRecordEntity.setItem(temp.getItemName());
		}else {
			creditRecordEntity.setItem(tempName);
		}	
		creditRecordEntity.setItemCode(temp.getItemCode());
		creditRecordEntity.setIntegralNumber(temp.getNum());//导入积分
		creditRecordEntity.setDateFlag(nowFlag);
		creditRecordEntity.setBeginDate(temp.getBeginDate()+"");
		creditRecordEntity.setEndDate(DateUtil.getFutureDayAsInt(String.valueOf(temp.getBeginDate()),cvp-1));//根据导入时的有效天数计算有效期
		creditRecordEntity.setGroupTime(DateUtil.Dateym(temp.getBeginDate()+""));//20200810
		creditRecordEntity.setRecordTime(new Date());
		creditRecordEntity.setImportMobile(temp.getMobile());
		creditRecordEntity.setSerialNum(temp.getSerialNum());
		creditRecordRepository.save(creditRecordEntity);

		// 5.个人信息累计总积分值

		MallUserInfoEntity myMallUserInfoEntity = mallUserInfoRepository.findByMallUserEntity(owner);		
		myMallUserInfoEntity.setCreditScore(myMallUserInfoEntity.getCreditScore()+temp.getNum());
		myMallUserInfoEntity.setSumScore(myMallUserInfoEntity.getSumScore()+temp.getNum());
		myMallUserInfoEntity.setModifytime(new Date());			
		mallUserInfoRepository.save(myMallUserInfoEntity);
	}
	//手动再次执行
	@Override
	@Transactional
	public void scanCrmErrorManual() {
		List<CRMCreditApiErrorMsgEntity> errLists = new ArrayList<CRMCreditApiErrorMsgEntity>();
		errLists = pageCrmCreditApiErrMsgRepository.findBySta(0);
		ParamEntity p =paramRepository.getValueByCode("cvp");
		int cvp = Integer.parseInt(p.getValue());//当前有效期天数
		for(CRMCreditApiErrorMsgEntity err:errLists) {
			List<CreditRecordEntity> l = creditRecordRepository.findBySerialNum(err.getSerialNum());	
			if(l.size() == 0) {
			//查询正式表中是否有流水号 ，没有的话 再 插入
			CRMCreditRecordEntity entity = pageCrmCreditRecordRepository.findBySerialNum(err.getSerialNum());
			//再次校验各字段
			int flag = 0;
			String msg ="";
			int endDate = DateUtil.getFutureDayAsInt(String.valueOf(entity.getBeginDate()),cvp-1);

			if( entity.getClientName() == null|| entity.getDepartmentCode() == null|| entity.getDepartmentDesc()== null|| 
					entity.getItemName() == null|| entity.getItemCode() == null || String.valueOf(entity.getNum())== null || String.valueOf(entity.getBeginDate())== null ) {
				flag=+1;
				msg =msg+":存在空字段；";
			}
			
			if(String.valueOf(entity.getBeginDate()).length() != 8 ) {
				flag=+1;
				msg =msg+":导入日期格式错误；";
			}
			if(entity.getBeginDate()>Integer.parseInt(DateUtil.Dateymd(new Date()))) {
				flag=+1;
				msg =msg+"生效日期大于当前日期；";//不会扫到，只会扫描t-1
			}
			if(endDate <= DateUtil.getPreDayAsInt(1)) {
				flag=+1;
				msg =msg+"积分截止日期小于当前日期，请调整积分有效期参数；";
			}	
			//判断 生效日期不能大于当天
			if(endDate < entity.getBeginDate() ) {
				flag=+1;
				msg =msg+"积分截止日期小于生效日期；";
			}
			

			if( entity.getNum() <= 0) {
				flag=+1;
				msg =msg+":分数为非正数；";
			}
			User user = UserManageUtil.getUser();
			String userName = user.getUsername();
			if(flag > 0) {
				err.setMsg(msg);
				err.setModifyTime(new Date());
				err.setModifyBy(userName);
			}else {
				//未报错，接口数据开始转换正式数据
				try {
					changeToCreditRecord(entity);
					//将此条记录置位 已处理状态
					
					err.setModifyTime(new Date());
					err.setModifyBy(userName);
					err.setSta(1);
					pageCrmCreditApiErrMsgRepository.save(err);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					err.setMsg("手动执行接口转换过程未知异常");
					err.setModifyBy(userName);
					err.setModifyTime(new Date());
					pageCrmCreditApiErrMsgRepository.save(err);
				}
				
			}
			}else {
				//已经处理，不需要这里再次手动推送
				err.setModifyTime(new Date());
				
				err.setSta(1);
				pageCrmCreditApiErrMsgRepository.save(err);
			}
		}
	
		
	}
	@Override
	@Transactional
	public void mailSendCreditJob() {
		//查询积分项目，拼接邮件内容ParamEntity
		String from = paramRepository.getValueByCode("mailSender").getValue();
		String to = paramRepository.getValueByCode("mailReceiver").getValue();
		String authorizationCode = paramRepository.getValueByCode("mailAuthorizationCode").getValue();
		String smtpServer = paramRepository.getValueByCode("smtpServer").getValue();
		String emailMsg = "";
		String date = DateUtil.getPreDayAsString();
		List<CreditRecordEntity> recordList = new ArrayList<CreditRecordEntity>();
		List<MailItemListEntity> itemList = new ArrayList<MailItemListEntity>();
		//1-每天都 检查，0-每月第一天检查
		if("01".equals(date.substring(8))) {
			 itemList = mailItemListRepository.findAll();//每月第一天查全量
		}else {
			 itemList = mailItemListRepository.findByFlag(1);//每天查三个项目 
			// itemList = mailItemListRepository.findAll();
		}
		for(MailItemListEntity item : itemList) {
			recordList = creditRecordRepository.findByTypeAndItemCodeAndDateFlag(true, item.getItemCode(), date);
			if(recordList.size() == 0 ) {
				emailMsg = emailMsg + item.getItemCode()+"——"+item.getItemName()+" ; <br>";
				
				recordList.clear();
			}
		}
		//添加正文前缀
		//emailMsg = "test-"+new Date();
		if(!"".equals(emailMsg)){
			emailMsg = "此邮件为系统自动发送，请勿回复！<br>查询日期为： "+date+" ,"+"以下积分项目无数据更新： <br>"+emailMsg+"请排查crm系统是否正常生成数据。";
			//调用邮件发送工具类
			SendEmailUtil.sendMail(from, to,
					emailMsg, authorizationCode, smtpServer);
		}
		
		
	}

	
}
