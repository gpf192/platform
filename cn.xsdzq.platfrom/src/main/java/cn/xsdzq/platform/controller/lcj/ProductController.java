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
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.lcj.ProductSellEntity;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.model.lcj.ProductSellDTO;
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
		//logger.info("action:" + "add" + ";" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteProduct", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletInfo(HttpServletRequest request, @RequestBody ProductDTO dto) {
		ProductEntity entity = LcjUtil.convertEntityByProductDTO(dto);	
		
		productService.deleteProduct(entity);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info(" 删除理财节 产品  product 信息 action:" + "delete" + ";" + "user: " + name  +" productname: "+entity.getName()+" ;" );
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyProduct", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, @RequestBody ProductDTO dto) {
		ProductEntity entity = LcjUtil.convertEntityByProductDTO(dto);		
		
		productService.modifyProduct(entity);
		//logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}
//产品销售数据查询
	@RequestMapping(value = "/getProductSell", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getProductSell(HttpServletRequest request,  
			@RequestParam String clientId, @RequestParam String code, 
			@RequestParam String account, @RequestParam String finaccount, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询产品销售信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		String zj = account;
		String lc = finaccount;
		int sum = 0 ;
		List<ProductSellEntity> entitys = null;
		int num = MethodUtil.getProductSellMethodNum(clientId, code, zj,lc);
		if(num == 1) {
			//全量查找
		
			entitys = myProductSellService.getAllProductSell(pageNumber, pageSize);
			sum = myProductSellService.countAll();
		}
		if(num == 2) {
			//4个条件一起查询
			System.out.println("into   2 ____");
			entitys = myProductSellService.findByClientIdAndCodeAndZjAndLcOrderById(clientId, code, zj, lc, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndCodeAndZjAndLc(clientId, code, zj, lc);
		}
		if(num == 3) {
			//查询条件：clientId\code\zj\
			entitys = myProductSellService.findByClientIdAndCodeAndZjOrderById(clientId, code, zj, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndCodeAndZj(clientId, code, zj);
		}
		if(num == 4) {
			//查询条件：clientId\code\\lc
			entitys = myProductSellService.findByClientIdAndCodeAndLcOrderById(clientId, code, lc, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndCodeAndLc(clientId, code, lc);
		}
		if(num == 5) {
			//查询条件：clientId\\zj\lc
			entitys = myProductSellService.findByClientIdAndZjAndLcOrderById(clientId, zj, lc, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndZjAndLc(clientId, zj, lc);
		}
		if(num == 6) {
			//查询条件：\\code\zj\lc
			entitys = myProductSellService.findByCodeAndZjAndLcOrderById(code, zj, lc, pageNumber, pageSize);
			sum = myProductSellService.countByCodeAndZjAndLc(code, zj, lc);
		}
		if(num == 7) {
			////查询条件：clientId\code\\
			entitys = myProductSellService.findByClientIdAndCodeOrderById(clientId, code, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndCode(clientId, code);
		}
		if(num == 8) {
			//查询条件：clientId\\zj\
			entitys = myProductSellService.findByClientIdAndZjOrderById(clientId, zj, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndZj(clientId, zj);
		}
		if(num == 9) {
			//clientId\\\lc
			entitys = myProductSellService.findByClientIdAndLcOrderById(clientId, lc, pageNumber, pageSize);
			sum = myProductSellService.countByClientIdAndLc(clientId, lc);
		}
		if(num == 10) {
			//查询条件：\code\zj\
			entitys = myProductSellService.findByCodeAndZjOrderById(code, zj, pageNumber, pageSize);
			sum = myProductSellService.countByCodeAndZj(code, zj);
		}
		if(num == 11) {
			//查询条件  \code\\lc
			entitys = myProductSellService.findByCodeAndLcOrderById(code, lc, pageNumber, pageSize);
			sum = myProductSellService.countByCodeAndLc(code, lc);
		}
		if(num == 12) {
			//查询条件：\\\\zj\lc
			entitys = myProductSellService.findByZjAndLcOrderById(zj, lc, pageNumber, pageSize);
			sum = myProductSellService.countByZjAndLc(zj, lc);
		}
		if(num == 13) {
			//查询条件：clientId 
			entitys = myProductSellService.findByClientIdOrderById(clientId, pageNumber, pageSize);
			sum = myProductSellService.countByClientId(clientId);
		}
		if(num == 14) {
			////查询条件： \code
			entitys = myProductSellService.findByCodeOrderById(code, pageNumber, pageSize);
			sum = myProductSellService.countByCode(code);
		}
		if(num == 15) {
			//查询条件：zj
			entitys = myProductSellService.findByZjOrderById(zj, pageNumber, pageSize);
			sum = myProductSellService.countByZj(zj);
		}
		if(num == 16) {
			//查询条件：lc
			entitys = myProductSellService.findByLcOrderById(lc, pageNumber, pageSize);
			sum = myProductSellService.countByLc( lc);
		}
		
					
		List<ProductSellDTO> productSellDTOs = new ArrayList<ProductSellDTO>();
		for (ProductSellEntity entity : entitys) {
			ProductSellDTO dto = LcjUtil.convertProductSellDTOByEntity(entity);
			productSellDTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", productSellDTOs, pagination);
	}
}
