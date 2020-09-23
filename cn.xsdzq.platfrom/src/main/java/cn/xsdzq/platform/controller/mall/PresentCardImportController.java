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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;
import cn.xsdzq.platform.entity.mall.CreditImportTempEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.CardImportTempDTO;
import cn.xsdzq.platform.model.mall.CreditImportTempDTO;
import cn.xsdzq.platform.service.mall.CreditService;
import cn.xsdzq.platform.service.mall.PageCardImportTempService;
import cn.xsdzq.platform.service.mall.PresentCardService;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.DateUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.ImportExcelUtil;
import cn.xsdzq.platform.util.PublicUtil;
import cn.xsdzq.platform.util.UserManageUtil;
import cn.xsdzq.platform.util.mall.CreditUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;
import javassist.expr.NewArray;

@RestController
@RequestMapping(value = "/mall/cardImport")
public class PresentCardImportController {
	private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
	@Qualifier("pageCardImportTempServiceImpl")
	private PageCardImportTempService pageCardImportTempService;
	

	@Autowired
	@Qualifier("presentServiceImpl")
	private PresentService presentService;
	
	@Autowired
	private PresentCardService presentCardService;
	
	//导入excel
	 @RequestMapping(value="/uploadCardTemp",method={RequestMethod.POST}) 
	 public Map<String, Object>  uploadExcel(HttpServletRequest request) throws Exception { 
		 System.out.println("进入接口===================================(**********************************");
		 //
		 MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request); 
		 InputStream in =null; 
		  List<List<Object>> listob = null; 
		 MultipartFile file = multipartRequest.getFile("upfile"); 
		
		  if(file.isEmpty()){ 
		   throw new Exception("文件不存在！"); 
		  } 
		  in = file.getInputStream(); 
		  listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename()); 

		  in.close(); 
		System.out.println("end------");
		System.out.println("listob.size(): "+listob.size());//少后10条数据？
		  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出 
		  for (int i = 1; i < listob.size(); i++) { //i=0 则包括第一行标题数据
		   List<Object> lo = listob.get(i); //每行数据
		   //循环每行数据并插入
		   CardImportTempEntity entity = PresentUtil.toCardImportTempEntity(lo);
		   System.out.println("temp : "+entity.toString());
		   pageCardImportTempService.add(entity);
		  } 
		
		  return GsonUtil.buildMap(0, "success", null);
		} 
	//清空临时数据
	 @RequestMapping(value = "/deleteCardTempData", method = POST, produces = "application/json; charset=utf-8")
		@ResponseBody
		public Map<String, Object> deleteCardTempData() {
			List<CardImportTempEntity> temps = pageCardImportTempService.findAllTemp();
			  if(temps.size()==0) {
					return GsonUtil.buildMap(1, "当前无数据", null);	 
			  }
			  pageCardImportTempService.deleteAllTemp();
			return GsonUtil.buildMap(0, "success", null);
		}
	 
	 
	//查询临时导入数据
	@GetMapping(value = "/getCardImportTemp")
	public Map<String, Object> getCreditImportTemp(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		List<CardImportTempEntity> presentCardEntities = pageCardImportTempService.findByOrderByCardId(pageNumber, pageSize);
		List<CardImportTempDTO> dtos = new ArrayList<CardImportTempDTO>();
		for (CardImportTempEntity entity : presentCardEntities) {
			CardImportTempDTO dto = PresentUtil.convertCardImportTempDTOByEntity(entity);
			dtos.add(dto);
		}
		sum = pageCardImportTempService.countAll();
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	//正式提交
	@RequestMapping(value = "/submit", method = POST, produces = "application/json; charset=utf-8")	
	@ResponseBody
	public Map<String, Object> submit() {
		//查询临时表数据
		List<CardImportTempEntity> temps =  new ArrayList<CardImportTempEntity>();
		temps = pageCardImportTempService.findAllTemp();
		System.out.println("size :"+temps.size());
		//判断数据格式
		  //判断字段格式
		  if(temps.size()==0) {
				return GsonUtil.buildMap(1, "当前无数据，请先导入", null);
 
		  }
		//检查当前临时表中cardId是否有重复
		  if(PresentUtil.isRepeat(temps)) {
			  return GsonUtil.buildMap(1, "附件中"+"cardId:"+"有重复，无法导入", null);
		  }
		for (CardImportTempEntity entity : temps) {
			PresentEntity present =  presentService.getPresentEntitiesByCode(entity.getPresentCode());
			if(present == null) {
				return GsonUtil.buildMap(1, "父类代码:"+entity.getPresentCode()+"不存在，无法导入", null);
			}
			//检查cardId是否存在
			PresentCardEntity card = presentCardService.findByCardId(entity.getCardId());
			if(card != null) {		
				return GsonUtil.buildMap(1, "cardId:"+entity.getCardId()+"已存在，无法导入", null);
			}
			
			
		}
			User user = UserManageUtil.getUser();
			String username = user.getUsername();
		//循环每条数据并插入正式表，同时总分表加上相应数据,判断导入数据的时效性		
		for(CardImportTempEntity temp:temps) {
			 //临时类转换为 正式类，插入card表 end	
			PresentCardEntity presentCard = PresentUtil.cardTempEntityToCardEntity(temp);
			PresentEntity present =  presentService.getPresentEntitiesByCode(temp.getPresentCode());
			presentCard.setPresent(present);//关联父类
			presentCard.setCreateDate(new Date());
			presentCard.setCreatedBy(username);
			presentCard.setIsImport(1);
			presentCardService.addPresentCard(presentCard);
		}
		System.out.println("总记录插入完成----");
		//最后清空临时表数据
		pageCardImportTempService.deleteAllTemp();
		System.out.println("清空临时表入完成----");
		return GsonUtil.buildMap(0, "success", null);
	}
}
