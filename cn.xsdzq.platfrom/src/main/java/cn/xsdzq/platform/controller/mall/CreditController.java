package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import cn.xsdzq.platform.entity.mall.CreditEntity;
import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.CreditDTO;
import cn.xsdzq.platform.model.mall.CreditImportRecordDTO;
import cn.xsdzq.platform.model.mall.CreditImportTempDTO;
import cn.xsdzq.platform.model.mall.CreditUserTotalDTO;
import cn.xsdzq.platform.service.mall.CreditImportRecordService;
import cn.xsdzq.platform.service.mall.CreditImportTempService;
import cn.xsdzq.platform.service.mall.CreditService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.ImportExcelUtil;
import cn.xsdzq.platform.util.PublicUtil;
import cn.xsdzq.platform.util.mall.CreditUtil;

@RestController
@RequestMapping(value = "/mall/credit")
public class CreditController {
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
	@Qualifier("creditServiceImpl")
	private CreditService creditService;
	
	
	@Autowired
	@Qualifier("creditImportRecordServiceImpl")
	private CreditImportRecordService creditImportRecordService;
	
	@Autowired
	@Qualifier("creditImportTempServiceImpl")
	private CreditImportTempService creditImportTempService;
	
	@Autowired
	private MallUserService mallUserService;

	
	@PostMapping(value = "/add")
	public Map<String, Object> addCredit(@RequestBody CreditDTO creditDTO) {

		//logger.info(CreditDTO.toString());
		CreditEntity entity = CreditUtil.convertCreditEntityByDTO(creditDTO);
		creditService.addCredit(entity);
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getCredit() {

		List<CreditEntity> entities = creditService.getCreditEntities();
		List<CreditDTO> cDtos = new ArrayList<CreditDTO>();
		for (CreditEntity entity : entities) {
			CreditDTO dto = CreditUtil.convertCreditDTOByEntity(entity);
			cDtos.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
	

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCredit(@RequestBody CreditDTO creditDTO) {
		//有子分类不可删除
		//CreditEntity entity = CreditUtil.convertCreditEntityByDTO(creditDTO);
		creditService.deleteCredit(creditDTO.getId());
		return GsonUtil.buildMap(0, "success", null);
	}
	
	
	//查询用户总积分数
	@GetMapping(value = "/getUserCreditTotal")
	public Map<String, Object> getUserCreditTotal(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<MallUserInfoEntity> presentCardEntities = mallUserService.findByOrderByCreditScoreDesc(pageNumber, pageSize);
		List<CreditUserTotalDTO> dtos = new ArrayList<CreditUserTotalDTO>();
		for (MallUserInfoEntity entity : presentCardEntities) {
			CreditUserTotalDTO dto = CreditUtil.convertCreditUserTotalDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = mallUserService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	
	//查询用户积分导入明细
	@GetMapping(value = "/getCreditImportRecord")
	public Map<String, Object> getCreditImportRecord(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<CreditRecordEntity> presentCardEntities = creditImportRecordService.findByOrderByBeginDateDesc(pageNumber, pageSize);
		List<CreditImportRecordDTO> dtos = new ArrayList<CreditImportRecordDTO>();
		for (CreditRecordEntity entity : presentCardEntities) {
			CreditImportRecordDTO dto = CreditUtil.convertCreditImportRecordDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = creditImportRecordService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	//导入excel
	 @RequestMapping(value="/upload",method={RequestMethod.GET,RequestMethod.POST}) 
	 public Map<String, Object>  uploadExcel(HttpServletRequest request) throws Exception { 
		 System.out.println("进入接口===================================(**********************************");
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		  InputStream in =null; 
		  List<List<Object>> listob = null; 
		  MultipartFile file = multipartRequest.getFile("upfile"); 
		  System.out.println("111------"+file.getOriginalFilename()+" size: "+file.getSize()+" tostring: "+file.toString());
	
		  if(file.isEmpty()){ 
		   throw new Exception("文件不存在！"); 
		  } 

		  in = file.getInputStream(); 
		  System.out.println("2222------");
		  listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename()); 
			System.out.println("5555------");

		  in.close(); 
		System.out.println("end------");
		System.out.println("listob.size(): "+listob.size());//少后10条数据？
		  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出 
		  for (int i = 1; i < listob.size(); i++) { //i=0 则包括第一行标题数据
		   List<Object> lo = listob.get(i); //每行数据
		   //循环每行数据并插入
		   CreditImportTempEntity entity = CreditUtil.toCreditImportTempEntity(lo);
		   System.out.println("temp : "+entity.toString());
		   creditImportTempService.add(entity);
		  } 
		
		  return GsonUtil.buildMap(0, "success", null);
		} 
	//清空临时数据
	 @RequestMapping(value = "/deleteTempData", method = POST, produces = "application/json; charset=utf-8")
		@ResponseBody
		public Map<String, Object> deleteTempData() {
		 	creditImportTempService.deleteAllTemp();
			return GsonUtil.buildMap(0, "success", null);
		}
	 
	 
	//查询临时导入数据
	@GetMapping(value = "/getCreditImportTemp")
	public Map<String, Object> getCreditImportTemp(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<CreditImportTempEntity> presentCardEntities = creditImportTempService.findByOrderByBeginDateDesc(pageNumber, pageSize);
		List<CreditImportTempDTO> dtos = new ArrayList<CreditImportTempDTO>();
		for (CreditImportTempEntity entity : presentCardEntities) {
			CreditImportTempDTO dto = CreditUtil.convertCreditImportTempDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = creditImportTempService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	//正式提交
	@RequestMapping(value = "/submit", method = POST, produces = "application/json; charset=utf-8")	
	@ResponseBody
	public Map<String, Object> submit() {
		//查询临时表数据
		List<CreditImportTempEntity> temps = creditImportTempService.findAllTemp();
		System.out.println("size :"+temps.size());
		//判断数据格式
		  //判断字段格式
		  
			for (CreditImportTempEntity entity : temps) {
				if(!PublicUtil.isInteger(entity.getNum())) {
					//num不为数字
					return GsonUtil.buildMap(1, "client_id:"+entity.getClientId()+"导入积分格式错误", null);
				}
				if(!PublicUtil.isInteger(entity.getBeginDate()) || !PublicUtil.isInteger(entity.getEndDate()) ) {
					return GsonUtil.buildMap(1, "client_id:"+entity.getClientId()+"导入日期格式错误", null);

				}
				if(Integer.parseInt(entity.getEndDate()) < Integer.parseInt(DateUtil.Dateymd(new Date())) ) {
					return GsonUtil.buildMap(1, "client_id:"+entity.getClientId()+"失效日期小于当前日期，无法导入", null);

				}
			}
			
		//循环每条数据并插入正式表，同时总分表加上相应数据,判断导入数据的时效性
		//判断导入数据的失效日期，如果小于当天 ，提示无法导入 
		
		for(CreditImportTempEntity temp:temps) {
			//插入用户表，插入总分表   //插入正式记录表 end	
			mallUserService.addCreditScore(temp);								
		}
		System.out.println("总记录插入完成----");
		//最后清空临时表数据
		creditImportTempService.deleteAllTemp();
		System.out.println("清空临时表入完成----");
		return GsonUtil.buildMap(0, "success", null);
	}
	
}
