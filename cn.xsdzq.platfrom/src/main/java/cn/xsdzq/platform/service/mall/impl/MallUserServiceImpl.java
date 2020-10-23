package cn.xsdzq.platform.service.mall.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.CreditCategoryRepository;
import cn.xsdzq.platform.dao.mall.CreditRecordRepository;
import cn.xsdzq.platform.dao.mall.MallUserInfoRepository;
import cn.xsdzq.platform.dao.mall.MallUserRepository;
import cn.xsdzq.platform.dao.mall.PageCrmCreditApiErrMsgRepository;
import cn.xsdzq.platform.dao.mall.PageCrmCreditRecordRepository;
import cn.xsdzq.platform.dao.mall.PageMallUserInfoRepository;
import cn.xsdzq.platform.entity.mall.CRMCreditApiErrorMsgEntity;
import cn.xsdzq.platform.entity.mall.CRMCreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.service.mall.CRMCreditRecordService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.PublicUtil;
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
		creditRecordEntity.setEndDate(Integer.parseInt(temp.getEndDate()));
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

	@Override
	@Transactional
	public void endDateJob() {
		// TODO Auto-generated method stub
		int preday =DateUtil.getPreDayAsInt();
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
	}

	@Override
	@Transactional
	public void scanCrmCreditJob() {
		// TODO Auto-generated method stub
		//扫描crmrecord 前一天记录
		int preday =DateUtil.getPreDayAsInt(); 
		List<CRMCreditRecordEntity> crmlist = new ArrayList<CRMCreditRecordEntity>();
		crmlist = pageCrmCreditRecordRepository.findByBeginDate(preday);
		
		System.out.println("crm 有几条记录"+crmlist.size());
		//循环，校验数据，不合格的 插入error表,合格的插入record表，
		for(CRMCreditRecordEntity entity:crmlist) {
			String client_id = entity.getClientId();
			int flag = 0;
			String msg ="";
			if( entity.getClientName() == null|| entity.getDepartmentCode() == null|| entity.getDepartmentDesc()== null|| 
					entity.getItemName() == null|| entity.getItemCode() == null || String.valueOf(entity.getNum())== null || String.valueOf(entity.getBeginDate())== null || String.valueOf(entity.getEndDate())== null) {
				flag=+1;
				msg =msg+":存在空字段；";
			}
			
			if(String.valueOf(entity.getBeginDate()).length() != 8 || String.valueOf(entity.getEndDate()).length() != 8) {
				flag=+1;
				msg =msg+":导入日期格式错误；";
			}
			if(entity.getEndDate() < Integer.parseInt(DateUtil.Dateymd(new Date())) ) {
				flag=+1;
				msg =msg+":失效日期小于当前日期；";
			}	

			if(entity.getEndDate() < entity.getBeginDate() ) {
				flag=+1;
				msg =msg+":失效日期小于生效日期；";
			}
			if( entity.getNum() <= 0) {
				flag=+1;
				msg =msg+":分数为非正数；";
			}
			if(flag > 0) {
				System.out.println("crm 校验数据完毕，发现错误");
				//插入错误表
				CRMCreditApiErrorMsgEntity errorEntity = new CRMCreditApiErrorMsgEntity();
				errorEntity.setMsg(msg);
				errorEntity.setSerialNum(entity.getSerialNum());
				errorEntity.setRecordTime(new Date());
				errorEntity.setSta(0);
				pageCrmCreditApiErrMsgRepository.save(errorEntity);
			}else {
				//接口数据转化为正式数据
				System.out.println("crm 校验数据完毕，开始转换正式数据");
				try {
					changeToCreditRecord(entity);
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
	//接口数据转化为正式数据
	public void changeToCreditRecord(CRMCreditRecordEntity temp) {
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
		creditRecordEntity.setEndDate(temp.getEndDate());
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
		for(CRMCreditApiErrorMsgEntity err:errLists) {
			CRMCreditRecordEntity entity = pageCrmCreditRecordRepository.findBySerialNum(err.getSerialNum());
			//再次校验各字段
			int flag = 0;
			String msg ="";
			if( entity.getClientName() == null|| entity.getDepartmentCode() == null|| entity.getDepartmentDesc()== null|| 
					entity.getItemName() == null|| entity.getItemCode() == null || String.valueOf(entity.getNum())== null || String.valueOf(entity.getBeginDate())== null || String.valueOf(entity.getEndDate())== null) {
				flag=+1;
				msg =msg+":存在空字段；";
			}
			
			if(String.valueOf(entity.getBeginDate()).length() != 8 || String.valueOf(entity.getEndDate()).length() != 8) {
				flag=+1;
				msg =msg+":导入日期格式错误；";
			}
			if(entity.getEndDate() < Integer.parseInt(DateUtil.Dateymd(new Date())) ) {
				flag=+1;
				msg =msg+":失效日期小于当前日期；";
			}	

			if(entity.getEndDate() < entity.getBeginDate() ) {
				flag=+1;
				msg =msg+":失效日期小于生效日期；";
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
					err.setMsg(msg);
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
		}
		
	}
	
}
