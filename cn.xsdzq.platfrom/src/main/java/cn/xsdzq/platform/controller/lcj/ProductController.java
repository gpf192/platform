package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.lcj.CwSellViewEntity;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.model.lcj.ProductSellDTO;
import cn.xsdzq.platform.service.lcj.CwProductSellService;
import cn.xsdzq.platform.service.lcj.MyProductSellService;
import cn.xsdzq.platform.service.lcj.MyProductService;
import cn.xsdzq.platform.service.lcj.ProductService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	Logger logger = LogManager.getLogger(ProductController.class.getName());
	@Autowired
	@Qualifier("myProductServiceImpl")
	private MyProductService myProductService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("myProductSellServiceImpl")
	private MyProductSellService myProductSellService;
	
	@Autowired
	@Qualifier("cwProductSellServiceImpl")
	private CwProductSellService cwProductSellService;
	
	@RequestMapping(value = "/getProduct", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getProduct(HttpServletRequest request,  
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询产品信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");		
		int sum = 0 ;
		List<ProductDTO> productDTOs = null;
		try {
			List<ProductEntity> entitys = null;		
				entitys = myProductService.getAllProduct(pageNumber, pageSize);
				sum = myProductService.countAll();
						
			productDTOs = new ArrayList<ProductDTO>();
			for (ProductEntity entity : entitys) {
				ProductDTO dto = LcjUtil.convertProductDTOByEntity(entity);
				productDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", productDTOs, pagination);
	}

	@RequestMapping(value = "/addProduct", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest request, @Validated @RequestBody ProductDTO dto) {
		ProductEntity entity = LcjUtil.convertEntityByProductDTO(dto);	
		// 插入创建人
		
		productService.addProduct(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();	
		logger.info("action:" + "productAdd" + "; user: " + name + ";" + "productCode: " + entity.getCode() + " ;");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteProduct", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletInfo(HttpServletRequest request, @RequestBody ProductDTO dto) {
		ProductEntity entity = null;
		try {
			entity = LcjUtil.convertEntityByProductDTO(dto);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		try {
			productService.deleteProduct(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info(" 删除理财节 产品  product 信息 action:" + "delete" + ";" + "user: " + name  +" productCode: "+entity.getCode()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyProduct", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, @RequestBody ProductDTO dto) {
		ProductEntity entity = LcjUtil.convertEntityByProductDTO(dto);		
		
		productService.modifyProduct(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "productModify" + ";" + "user:" + name + ";" + "productCode:" + entity.getCode() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
//场内和场外产品销售数据查询
	@RequestMapping(value = "/getProductSell", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getProductSell(HttpServletRequest request,  
			@RequestParam String clientId, @RequestParam String productCode, 
			@RequestParam String financeAccount, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询产品销售信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		int sum = 0 ;
		List<ProductSellEntity> entitys = null;
		int num = MethodUtil.getProductSellMethodNum(clientId, productCode, financeAccount);
		if(num == 1) {
			//全量查找
		
			try {
				entitys = myProductSellService.getAllProductSell(pageNumber, pageSize);
				sum = myProductSellService.countAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(num == 2) {
			//3个条件一起查询
			System.out.println("into   2 ____");
			entitys = myProductSellService.findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(clientId, productCode, financeAccount, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndProductCodeAndFinanceAccount(clientId, productCode, financeAccount);
		}
		if(num == 3) {
			//查询条件：clientId\code\
			entitys = myProductSellService.findByClientIdAndProductCodeOrderByDealTimeDesc(clientId, productCode,  pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndProductCode(clientId, productCode);
		}
		if(num == 4) {
			//查询条件：clientId\\\financeAccount
			entitys = myProductSellService.findByClientIdAndFinanceAccountOrderByDealTimeDesc(clientId, financeAccount, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndFinanceAccount(clientId, financeAccount);
		}
		if(num == 5) {
			//查询条件：\code\financeAccount
			entitys = myProductSellService.findByProductCodeAndFinanceAccountOrderByDealTimeDesc(productCode, financeAccount, pageNumber, pageSize);
			sum = myProductSellService.countByProductCodeAndFinanceAccount(productCode, financeAccount);
		}
		if(num == 6) {
			//查询条件：//查询条件：clientId
			entitys = myProductSellService.findByClientIdOrderByDealTimeDesc(clientId, pageNumber, pageSize);
			sum = myProductSellService.countByClientId(clientId);
		}
		if(num == 7) {
			////查询条件：\code\\
			entitys = myProductSellService.findByProductCodeOrderByDealTimeDesc(productCode, pageNumber, pageSize);
			sum = myProductSellService.countByProductCode(productCode);
		}
		if(num == 8) {
			//查询条件：\\financeAccount\
			entitys = myProductSellService.findByFinanceAccountOrderByDealTimeDesc(financeAccount, pageNumber, pageSize);
			sum = myProductSellService.countByFinanceAccount(financeAccount);
		}
		
					
		List<ProductSellDTO> productSellDTOs = new ArrayList<ProductSellDTO>();
		for (ProductSellEntity entity : entitys) {
			ProductSellDTO dto = null;
			try {
				dto = LcjUtil.convertProductSellDTOByEntity(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productSellDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", productSellDTOs, pagination);
	}
	
	//场外产品销售数据查询
		@RequestMapping(value = "/getCwProductSell", method = GET, produces = "application/json; charset=utf-8")
		@ResponseBody
		public Map<String, Object> getCwProductSell(HttpServletRequest request,  
				@RequestParam String clientId, @RequestParam String productCode, 
				@RequestParam String financeAccount, 
				 @RequestParam int pageNumber,@RequestParam int pageSize) {
			System.out.println("全量查询产品销售信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
			int sum = 0 ;
			List<CwSellViewEntity> entitys = null;
			int num = MethodUtil.getProductSellMethodNum(clientId, productCode, financeAccount);
			if(num == 1) {
				//全量查找
			
				try {
					entitys = cwProductSellService.getAllProductSell(pageNumber, pageSize);
					sum = cwProductSellService.countAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(num == 2) {
				//3个条件一起查询
				System.out.println("into   2 ____");
				entitys = cwProductSellService.findByClientIdAndProductCodeAndFinanceAccountOrderByDealTimeDesc(clientId, productCode, financeAccount, pageNumber, pageSize);
				sum = cwProductSellService.countByClientIdAndProductCodeAndFinanceAccount(clientId, productCode, financeAccount);
			}
			if(num == 3) {
				//查询条件：clientId\code\
				entitys = cwProductSellService.findByClientIdAndProductCodeOrderByDealTimeDesc(clientId, productCode,  pageNumber, pageSize);
				sum = cwProductSellService.countByClientIdAndProductCode(clientId, productCode);
			}
			if(num == 4) {
				//查询条件：clientId\\\financeAccount
				entitys = cwProductSellService.findByClientIdAndFinanceAccountOrderByDealTimeDesc(clientId, financeAccount, pageNumber, pageSize);
				sum = cwProductSellService.countByClientIdAndFinanceAccount(clientId, financeAccount);
			}
			if(num == 5) {
				//查询条件：\code\financeAccount
				entitys = cwProductSellService.findByProductCodeAndFinanceAccountOrderByDealTimeDesc(productCode, financeAccount, pageNumber, pageSize);
				sum = cwProductSellService.countByProductCodeAndFinanceAccount(productCode, financeAccount);
			}
			if(num == 6) {
				//查询条件：//查询条件：clientId
				entitys = cwProductSellService.findByClientIdOrderByDealTimeDesc(clientId, pageNumber, pageSize);
				sum = cwProductSellService.countByClientId(clientId);
			}
			if(num == 7) {
				////查询条件：\code\\
				entitys = cwProductSellService.findByProductCodeOrderByDealTimeDesc(productCode, pageNumber, pageSize);
				sum = cwProductSellService.countByProductCode(productCode);
			}
			if(num == 8) {
				//查询条件：\\financeAccount\
				entitys = cwProductSellService.findByFinanceAccountOrderByDealTimeDesc(financeAccount, pageNumber, pageSize);
				sum = cwProductSellService.countByFinanceAccount(financeAccount);
			}
			
						
			List<ProductSellDTO> productSellDTOs = new ArrayList<ProductSellDTO>();
			for (CwSellViewEntity entity : entitys) {
				ProductSellDTO dto = null;
				try {
					dto = LcjUtil.convertProductSellDTOByEntity(entity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				productSellDTOs.add(dto);
			}
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", productSellDTOs, pagination);
		}
}
