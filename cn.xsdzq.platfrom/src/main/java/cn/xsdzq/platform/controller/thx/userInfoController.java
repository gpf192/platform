package cn.xsdzq.platform.controller.thx;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.lcj.ProductEntity;
import cn.xsdzq.platform.entity.thx.UserRiskEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.ProductDTO;
import cn.xsdzq.platform.service.lcj.MyProductService;
import cn.xsdzq.platform.service.thx.UserRiskService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;

@Controller
@RequestMapping("/thx")
public class userInfoController extends BaseController{
	Logger logger = LogManager.getLogger(userInfoController.class.getName());

	@Autowired
	@Qualifier("userRiskServiceServiceImpl")
	private UserRiskService userRiskService;
	
	@RequestMapping(value = "/getRiskInfo", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getRiskInfo(HttpServletRequest request,  
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户风险信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");		
		int sum = 0 ;
		List<ProductDTO> productDTOs = null;
		try {
			List<UserRiskEntity> entitys = null;		
				entitys = userRiskService.getAllUserRisk(pageNumber, pageSize);
				sum = userRiskService.countAll();
						
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

}
