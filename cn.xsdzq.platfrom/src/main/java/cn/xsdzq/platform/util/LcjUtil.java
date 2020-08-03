package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.LiveRecordEntity;
import cn.xsdzq.platform.entity.lcj.AwardEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;
import cn.xsdzq.platform.entity.lcj.AwardResultViewEntity;
import cn.xsdzq.platform.entity.lcj.CwSellViewEntity;
import cn.xsdzq.platform.entity.lcj.DepartmentEntity;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;
import cn.xsdzq.platform.entity.lcj.LcjPrizeResultViewEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultViewEntity;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;
import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;
import cn.xsdzq.platform.model.LiveRecordDTO;
import cn.xsdzq.platform.model.lcj.AwardDTO;
import cn.xsdzq.platform.model.lcj.AwardResultdDTO;
import cn.xsdzq.platform.model.lcj.DepartmentDTO;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.model.lcj.EmpVoteDTO;
import cn.xsdzq.platform.model.lcj.PrizeDTO;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.model.lcj.ProductSellDTO;
import cn.xsdzq.platform.model.lcj.UserVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteForDTO;
//奖项信息
public class LcjUtil {
	public static PrizeDTO convertPrizeDTOByPrize(PrizeEntity entity) {		
		PrizeDTO dto = new PrizeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		dto.setPrice(entity.getPrice());
		dto.setRate(entity.getRate());
		dto.setImage(entity.getImage());
		dto.setAmount(entity.getAmount());
		dto.setType(entity.isType());
		dto.setShow(entity.isShow());
		dto.setWinningNumber(entity.getWinningNumber());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return dto;
	}
	//818
	public static PrizeDTO convertPrizeDTOByLcjPrize(LcjPrizeEntity entity) {		
		PrizeDTO dto = new PrizeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		dto.setPrice(entity.getPrice());
		dto.setRate(entity.getRate());
		dto.setImage(entity.getImage());
		dto.setAmount(entity.getAmount());
		dto.setType(entity.isType());
		dto.setShow(entity.isShow());
		dto.setWinningNumber(entity.getWinningNumber());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return dto;
	}
	public static PrizeEntity convertEntityByPrizeDTO(PrizeDTO dto) {
		PrizeEntity entity = new PrizeEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());		
		entity.setPrice(dto.getPrice());	
		entity.setAmount(dto.getAmount());
		entity.setImage(dto.getImage());
		try {
			entity.setCreatetime(DateUtil.stringToDate1(dto.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
	public static LcjPrizeEntity convertLcjEntityByPrizeDTO(PrizeDTO dto) {
		LcjPrizeEntity entity = new LcjPrizeEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());		
		entity.setPrice(dto.getPrice());	
		entity.setAmount(dto.getAmount());
		entity.setImage(dto.getImage());
		try {
			entity.setCreatetime(DateUtil.stringToDate1(dto.getCreatetime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
	
	//中奖纪录--开门红
	public static PrizeRecordDTO convertPrizeRecordDTOByEntity(PrizeResultViewEntity entity) {		
		PrizeRecordDTO dto = new PrizeRecordDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getClientName());
		
		dto.setClientId(entity.getClientId());
		dto.setPrizeName(entity.getPrizeName());
		dto.setDepartName(entity.getDepartName());
		dto.setPrizeCode(entity.getPrizeCode());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	//818
	
	public static PrizeRecordDTO convertLcjPrizeResultDTOByEntity(LcjPrizeResultViewEntity entity) {		
		PrizeRecordDTO dto = new PrizeRecordDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getClientName());
		
		dto.setClientId(entity.getClientId());
		dto.setPrizeName(entity.getPrizeName());
		dto.setDepartName(entity.getDepartName());
		dto.setPrizeCode(entity.getPrizeCode());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	//产品信息
	public static ProductDTO convertProductDTOByEntity(ProductEntity entity) {		
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setCode(entity.getCode());
		dto.setCoefficient(entity.getCoefficient());
		
		try {
			dto.setBeginDate(DateUtil.DateToStringAsMonth(entity.getBeginDate()));
			dto.setEndDate(DateUtil.DateToStringAsMonth(entity.getEndDate()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setInitialAmount(entity.getInitialAmount());
		dto.setFlag(entity.getFlag());
		dto.setRiskLevel(entity.getRiskLevel());
		dto.setPreferentialInfo(entity.getPreferentialInfo());
		dto.setScanFlag(entity.getScanFlag());
		return dto;
	}
	public static ProductEntity convertEntityByProductDTO(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setType(dto.getType());
		entity.setCoefficient(dto.getCoefficient());
		
		try {
			entity.setBeginDate(DateUtil.stringToDate(dto.getBeginDate()));
			entity.setEndDate(DateUtil.stringToDate(dto.getEndDate()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entity.setInitialAmount(dto.getInitialAmount());
		entity.setFlag(dto.getFlag());
		entity.setRiskLevel(dto.getRiskLevel());
		entity.setPreferentialInfo(dto.getPreferentialInfo());
		entity.setScanFlag(dto.getScanFlag());
		return entity;
	}
	//场内+场外产品销售数据
	public static ProductSellDTO convertProductSellDTOByEntity(ProductSellEntity entity) {		
		ProductSellDTO dto = new ProductSellDTO();
		dto.setFlag(entity.getFlag());
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());		
		
		dto.setProductCode(entity.getProductCode());
		dto.setProductName(entity.getProductName());
		
		dto.setFinanceAccount(entity.getFinanceAccount());
		
		dto.setDealTime(entity.getDealTime());
		
		dto.setDealAmount(entity.getDealAmount());
		
		dto.setFlag(entity.getFlag());
		dto.setEmpName(entity.getEmpName());
		return dto;
	}
	
	//场外产品销售数据
	public static ProductSellDTO convertProductSellDTOByEntity(CwSellViewEntity entity) {		
		ProductSellDTO dto = new ProductSellDTO();
		dto.setFlag(entity.getFlag());
		dto.setClientId(entity.getClientId());
		dto.setClientName(entity.getClientName());		
		
		dto.setProductCode(entity.getProductCode());
		dto.setProductName(entity.getProductName());
		
		dto.setFinanceAccount(entity.getFinanceAccount());
		
		dto.setDealTime(entity.getDealTime());
		
		dto.setDealAmount(entity.getDealAmount());
		
		dto.setFlag(entity.getFlag());
		dto.setEmpName(entity.getEmpName());
		return dto;
	}
	//参赛人员信息
	public static EmpDTO convertEmpDTOByEntity(EmpEntity entity) {		
		EmpDTO dto = new EmpDTO();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmpName());
		dto.setEmp_code(entity.getEmpCode());
		dto.setEmp_type(entity.getEmpType());
		dto.setEmp_category(entity.getEmpCategory());
		dto.setEntry_time(entity.getEntryTime());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setSales_department(entity.getDepartmentEntity().getName());
		dto.setContract(entity.getContract());
		dto.setDivision(entity.getDivision());
		DepartmentEntity depart = entity.getDepartmentEntity();
		return dto;
	}
	public static EmpEntity convertEntityByEmpDTO(EmpDTO dto) {
		EmpEntity entity = new EmpEntity();
		entity.setId(dto.getId());
		entity.setEmpName(dto.getEmp_name());
		entity.setEmpId(dto.getEmp_code());
		entity.setEmpCode(dto.getEmp_code());
		entity.setEmpType(dto.getEmp_type());
		entity.setEmpCategory(dto.getEmp_category());
		entity.setEntryTime(dto.getEntry_time());
		entity.setDepartmentCode(dto.getDepartmentCode());
		entity.setContract(dto.getContract());
		entity.setDivision(dto.getDivision());

		return entity;
	}
	//用户得票数记录
	public static UserVoteDTO convertUserVoteDTOByEntity(UserTicketRecordEntity entity) {		
		UserVoteDTO dto = new UserVoteDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUserEntity().getClientName());
		dto.setClientId(entity.getUserEntity().getClientId());
		dto.setTotal_votes(entity.getNumber());
		dto.setVotes_source(entity.getVotesSource());
		dto.setSourceId(entity.getVotesSource());
		try {
			dto.setGain_time(DateUtil.DateToString(entity.getGainTime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*dto.setVote_for(entity.getVoteFor());
		dto.setVote_for_amount(entity.getVoteForAmount());
		dto.setDivision(entity.getDivision());	*/	
		//dto.setSales_department(entity.getSalesDepartment());
		return dto;
	}
	//参赛选手得票记录
	public static EmpVoteDTO convertEmpVoteDTOByEntity(EmpTicketEntity entity) {		
		EmpVoteDTO dto = new EmpVoteDTO();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmpEntity().getEmpName());
		dto.setEmp_code(entity.getEmpEntity().getEmpCode());
		dto.setWeight(entity.getWeight());
		dto.setDivision(entity.getEmpEntity().getDivision());		
		dto.setSales_department(entity.getEmpEntity().getDepartmentEntity().getName());
		dto.setGet_vote_amount(entity.getNumber());
		try {
			dto.setGet_vote_time(DateUtil.DateToString(entity.getModifytime()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public static EmpTicketEntity convertEmpVoteEntityByDTO(EmpVoteDTO dto) {		
		EmpTicketEntity entity = new EmpTicketEntity();
		entity.setId(dto.getId());
		//entity.setEmpName(dto.getEmp_name());
		//entity.setEmpCode(dto.getEmp_code());
		entity.setWeight(dto.getWeight());
		//entity.setDivision(dto.getDivision());		
		//entity.setSalesDepartment(dto.getSales_department());
		//entity.setGetVoteAmount(dto.getGet_vote_amount());
		//entity.setGetVoteTime(dto.getGet_vote_time());
		//entity.setVoteFromUser(dto.getVote_from_user());
		return entity;
	}
	
	//部门 department
	public static DepartmentEntity convertDepartmentEntityByDTO(DepartmentDTO dto) {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		
		return entity;
	}

	public static DepartmentDTO convertDepartmentDTOByEntity(DepartmentEntity entity) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		
		return dto;
	}
	//用户投票记录统计
	
	public static UserVoteForDTO convertUserVoteForDTOByEntity(UserVoteEmpResultEntity entity) {		
		UserVoteForDTO dto = new UserVoteForDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getUserEntity().getClientId());
		dto.setUsername(entity.getUserEntity().getClientName());
		dto.setVoteTime(DateUtil.DateToString(entity.getRecordTime()));
		dto.setEmpName(entity.getEmpEntity().getEmpName());
		dto.setEmpCode(entity.getEmpEntity().getEmpCode());
		dto.setVoteNum(entity.getNumber());
		dto.setDivision(entity.getEmpEntity().getDivision());		
		dto.setSalesDepartment(entity.getEmpEntity().getDepartmentEntity().getName());
		return dto;
	}
	public static AwardDTO convertAwardDTOByEntity(AwardEntity entity) {		
		AwardDTO dto = new AwardDTO();
		dto.setId(entity.getId());
		dto.setAwardName(entity.getAwardName());
		dto.setAwardNameAlias(entity.getAwardNameAlias());
		dto.setAwardValue(entity.getAwardValue());
		dto.setImageName(entity.getImageName());
		dto.setImageNumber(entity.getImageNumber());
		dto.setIndex(entity.getIndex());
				
		return dto;
	}
	public static AwardEntity convertEntityByAwardDTO(AwardDTO dto) {
		AwardEntity entity = new AwardEntity();
		entity.setId(dto.getId());
		entity.setAwardName(dto.getAwardName());
		entity.setAwardNameAlias(dto.getAwardNameAlias());
		entity.setAwardValue(dto.getAwardValue());
		entity.setImageName(dto.getImageName());
		entity.setImageNumber(dto.getImageNumber());
		entity.setIndex(dto.getIndex());
		return entity;
	}
	
	//开门红组合奖兑奖记录
	public static AwardResultdDTO convertAwardResultDTOByEntity(AwardResultViewEntity entity) {		
		AwardResultdDTO dto = new AwardResultdDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getClientName());
		
		dto.setClientId(entity.getClientId());
		dto.setPrizeName(entity.getPrizeName());
		dto.setAwardNum(entity.getAwardNumber());
		dto.setDepartName(entity.getDepartName());
		try {
			dto.setCreatetime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	//直播用户登录记录
	public static LiveRecordDTO convertLiveRecordDTOByEntity(LiveRecordEntity entity) {		
		LiveRecordDTO dto = new LiveRecordDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getUserEntity().getClientId());
	
		try {
			dto.setRecordTime(DateUtil.DateToString(entity.getRecordTime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
}