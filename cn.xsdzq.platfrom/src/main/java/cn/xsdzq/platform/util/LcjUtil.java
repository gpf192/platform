package cn.xsdzq.platform.util;

import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeRecordEntity;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.model.lcj.PrizeDTO;
import cn.xsdzq.platform.model.lcj.PrizeRecordDTO;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.model.lcj.ProductSellDTO;
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

		return entity;
	}
	//中奖纪录
	public static PrizeRecordDTO convertPrizeRecordDTOByPrizeRecord(PrizeRecordEntity entity) {		
		PrizeRecordDTO dto = new PrizeRecordDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		
		dto.setAccount(entity.getAccount());
		dto.setPrizeName(entity.getPrizeName());
		dto.setCreatetime(entity.getCreatetime());
		
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
		dto.setUsername(entity.getUsername());
		dto.setAccount(entity.getAccount());
		dto.setProduct_code(entity.getProduct_code());
		dto.setProduct_name(entity.getProduct_name());
		
		dto.setDeal_amount(entity.getDeal_amount());
		dto.setDeal_share(entity.getDeal_share());
		dto.setDeal_time(entity.getDeal_time());
		dto.setVotes(entity.getVotes());
		dto.setEmp_name(entity.getEmp_name());
		dto.setOrder_time(entity.getOrder_time());
		return dto;
	}
	//参赛人员信息
	public static EmpDTO convertEmpDTOByEntity(EmpEntity entity) {		
		EmpDTO dto = new EmpDTO();
		dto.setId(entity.getId());
		dto.setEmp_name(entity.getEmp_name());
		dto.setEmp_code(entity.getEmp_code());
		dto.setEmp_type(entity.getEmp_type());
		dto.setEmp_category(entity.getEmp_category());
		dto.setEntry_time(entity.getEntry_time());
		
		dto.setContract(entity.getContract());
		dto.setDivision(entity.getDivision());
		dto.setSales_department(entity.getSales_department());
		return dto;
	}
	public static EmpEntity convertEntityByEmpDTO(EmpDTO dto) {
		EmpEntity entity = new EmpEntity();
		entity.setId(dto.getId());
		entity.setEmp_name(dto.getEmp_name());
		entity.setEmp_code(dto.getEmp_code());
		entity.setEmp_type(dto.getEmp_type());
		entity.setEmp_category(dto.getEmp_category());
		entity.setEntry_time(dto.getEntry_time());
		
		entity.setContract(dto.getContract());
		entity.setDivision(dto.getDivision());
		entity.setSales_department(dto.getSales_department());

		return entity;
	}
}
