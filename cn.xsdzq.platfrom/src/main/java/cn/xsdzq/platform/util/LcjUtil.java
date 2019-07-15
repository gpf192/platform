package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.entity.lcj.PrizeResultEntity;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteForEntity;
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
		
		dto.setPice(entity.getPrice());
		dto.setRate(entity.getRate());
		dto.setAmount(entity.getAmount());
		dto.setWinning_number(entity.getWinning_number());
		/*try {
			dto.setCreatetime(DateUtil.DateToString(entity.getCreatetime()));		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return dto;
	}
	public static PrizeEntity convertEntityByPrizeDTO(PrizeDTO dto) {
		PrizeEntity entity = new PrizeEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPice());
		entity.setAmount(dto.getAmount());
		entity.setRate(dto.getRate());
		//entity.setWinning_number(dto.getWinning_number());
		return entity;
	}
	//中奖纪录
	public static PrizeRecordDTO convertPrizeRecordDTOByEntity(PrizeResultEntity entity) {		
		PrizeRecordDTO dto = new PrizeRecordDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUserEntity().getClientId());
		
		dto.setClientId(entity.getUserEntity().getClientId());
		dto.setPrizeName(entity.getPrizeEntity().getName());
		//dto.setCreatetime(entity.getCreatetime());
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
		dto.setBegin_date(entity.getBegin_date());
		dto.setCoefficient(entity.getCoefficient());
	
		
		return dto;
	}
	public static ProductEntity convertEntityByProductDTO(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		entity.setType(dto.getType());
		entity.setBegin_date(dto.getBegin_date());
		entity.setCoefficient(dto.getCoefficient());

		return entity;
	}
	//产品销售数据
	public static ProductSellDTO convertProductSellDTOByEntity(ProductSellEntity entity) {		
		ProductSellDTO dto = new ProductSellDTO();
		dto.setId(entity.getId());
		dto.setUsercode(entity.getUsercode());
		dto.setUsername(entity.getUsername());
		dto.setAccount(entity.getAccount());
		dto.setFinaccount(entity.getFinaccount());
		dto.setProduct_code(entity.getProductCode());
		dto.setProduct_name(entity.getProductName());
		dto.setProductType(entity.getProductType());
		dto.setFoundType(entity.getFoundType());
		dto.setDeal_amount(entity.getDealAmount());
		dto.setDeal_share(entity.getDealShare());
		dto.setDeal_time(entity.getDealTime());
		dto.setVotes(entity.getVotes());
		dto.setEmp_name(entity.getEmpName());
		dto.setOrder_time(entity.getOrderTime());
		return dto;
	}
	//参赛人员信息
	public static EmpDTO convertEmpDTOByEntity(EmpEntity entity) {		
		EmpDTO dto = new EmpDTO();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmpName());
		dto.setEmp_code(entity.getEmpName());
		dto.setEmp_type(entity.getEmpType());
		dto.setEmp_category(entity.getEmpCategory());
		dto.setEntry_time(entity.getEntryTime());
		dto.setDepartmentCode(entity.getDepartmentCode());
		dto.setContract(entity.getContract());
		dto.setDivision(entity.getDivision());
		DepartmentEntity depart = entity.getDepartmentEntity();
		dto.setSales_department(depart.getName());
		return dto;
	}
	public static EmpEntity convertEntityByEmpDTO(EmpDTO dto) {
		EmpEntity entity = new EmpEntity();
		entity.setId(dto.getId());
		entity.setEmpName(dto.getEmp_name());
		entity.setEmpCode(dto.getEmp_code());
		entity.setEmpType(dto.getEmp_type());
		entity.setEmpCategory(dto.getEmp_category());
		entity.setEntryTime(dto.getEntry_time());
		entity.setDepartmentCode(dto.getDepartmentCode());
		entity.setContract(dto.getContract());
		entity.setDivision(dto.getDivision());
		entity.setSalesDepartment(dto.getSales_department());

		return entity;
	}
	//用户得票数记录
	public static UserVoteDTO convertUserVoteDTOByEntity(UserVoteEntity entity) {		
		UserVoteDTO dto = new UserVoteDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setAccount(entity.getAccount());
		dto.setTotal_votes(entity.getTotalVotes());
		dto.setVotes_source(entity.getVotesSource());
		dto.setSourceId(entity.getSourceId());
		dto.setGain_time(entity.getGainTime());
		dto.setVote_for(entity.getVoteFor());
		dto.setVote_for_amount(entity.getVoteForAmount());
		dto.setDivision(entity.getDivision());		
		dto.setSales_department(entity.getSalesDepartment());
		return dto;
	}
	//参赛选手得票记录
	public static EmpVoteDTO convertEmpVoteDTOByEntity(EmpVoteEntity entity) {		
		EmpVoteDTO dto = new EmpVoteDTO();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmpName());
		dto.setEmp_code(entity.getEmpCode());
		dto.setWeight(entity.getWeight());
		dto.setDivision(entity.getDivision());		
		dto.setSales_department(entity.getSalesDepartment());
		dto.setGet_vote_amount(entity.getGetVoteAmount());
		dto.setGet_vote_time(entity.getGetVoteTime());
		dto.setVote_from_user(entity.getVoteFromUser());
		return dto;
	}
	
	public static EmpVoteEntity convertEmpVoteEntityByDTO(EmpVoteDTO dto) {		
		EmpVoteEntity entity = new EmpVoteEntity();
		entity.setId(dto.getId());
		entity.setEmpName(dto.getEmp_name());
		entity.setEmpCode(dto.getEmp_code());
		entity.setWeight(dto.getWeight());
		entity.setDivision(dto.getDivision());		
		entity.setSalesDepartment(dto.getSales_department());
		entity.setGetVoteAmount(dto.getGet_vote_amount());
		entity.setGetVoteTime(dto.getGet_vote_time());
		entity.setVoteFromUser(dto.getVote_from_user());
		return entity;
	}
	
	//部门 department
	public static DepartmentEntity convertDepartmentEntityByDTO(DepartmentDTO dto) {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		
		return entity;
	}

	public static DepartmentDTO convertDepartmentDTOByEntity(DepartmentEntity entity) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		
		return dto;
	}
	//用户投票记录统计
	
	public static UserVoteForDTO convertUserVoteForDTOByEntity(UserVoteForEntity entity) {		
		UserVoteForDTO dto = new UserVoteForDTO();
		dto.setId(entity.getId());
		dto.setClientId(entity.getClientId());
		dto.setUsername(entity.getUsername());
		dto.setVoteTime(entity.getVoteTime());
		dto.setEmpName(entity.getEmpName());
		dto.setEmpCode(entity.getEmpCode());
		dto.setVoteNum(entity.getVoteNum());
		dto.setDivision(entity.getDivision());		
		dto.setSalesDepartment(entity.getSalesDepartment());
		return dto;
	}
}