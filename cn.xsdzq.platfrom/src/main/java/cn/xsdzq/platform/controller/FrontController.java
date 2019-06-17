package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.CustomerMobileEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.KCBean;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.SearchBean;
import cn.xsdzq.platform.service.CustomerKCService;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.service.IMyInfoService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.CommonUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;

@Controller
@RequestMapping("/front")
public class FrontController {

	Logger logger = LogManager.getLogger(FrontController.class);

	@Autowired
	@Qualifier("myInfoServiceImpl")
	private IMyInfoService myInfoService;

	@Autowired
	@Qualifier("infoServiceImpl")
	private IInfoService iInfoService;

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;
	
	@Autowired
	@Qualifier("customerKCServiceImpl")
	private CustomerKCService customerKCService;

	@RequestMapping(value = "/getInfoById", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategory(HttpServletRequest request, @RequestParam long id) {
		if (id > 0) {
			InfoEntity info = myInfoService.getInfoEntityById(id);
			logger.info(CommonUtil.getIpAdrress(request));
			if (info != null) {
				InfoDTO infoDTO = InfoUtil.convertInfoDTOByInfo(info);
				// iInfoService.addPageViewById(id);
				myInfoService.addPageViewById(id);
				return GsonUtil.buildMap(0, "ok", infoDTO);
			}
		}
		return GsonUtil.buildMap(1, "fail", null);
	}

	@RequestMapping(value = "/getCategories", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllCategory() {
		logger.info("getAllCategory");
		List<CategoryEntity> list = categoryService.findAll();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}

	@RequestMapping(value = "/getDisplayCategories", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getDisplayCategories() {
		List<CategoryEntity> list = categoryService.findDisplayCategory();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}

	@RequestMapping(value = "/getInfosByCategoryId", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getInfos(HttpServletRequest request, @RequestParam long id, @RequestParam int pageNumber,
			@RequestParam int pageSize) {
		logger.info(id + "********");
		long categoryId = id;
		if (categoryId > 0) {
			List<InfoEntity> infos = myInfoService.getInfosByCategoryId(categoryId, pageNumber, pageSize);
			List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
			for (InfoEntity info : infos) {
				InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
				infoDTOs.add(dto);
			}
			int sum = myInfoService.countInfosByCategoryId(categoryId);
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", infoDTOs, pagination);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	// h5前端调用接口
	// 查询常见文章 ，条件：常用文章flag 、 审核状态为approve
	@RequestMapping(value = "/getCommonInfos", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCommonInfos(HttpServletRequest request, @RequestParam int pageNumber,
			@RequestParam int pageSize) {
		List<InfoEntity> infos = myInfoService.getInfosByCommonFlag("Y", pageNumber, pageSize);
		List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
		for (InfoEntity info : infos) {
			InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
			infoDTOs.add(dto);
		}
		int sum = myInfoService.countInfosByCommonFlag("Y");
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", infoDTOs, pagination);
	}

	@RequestMapping(value = "/search", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest request, @RequestBody SearchBean searchBean) {
		logger.info(searchBean.getKey());
		String key = searchBean.getKey();
		List<InfoEntity> infos = iInfoService.searchInfos(key);
		List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
		for (InfoEntity info : infos) {
			InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
			infoDTOs.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}
	
	//科创板预约
	
	@RequestMapping(value = "/saveCustomerMobile", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveCustomerMobile(HttpServletRequest request,  @RequestBody KCBean kcBean) {
		String name = kcBean.getName();
		String phone = kcBean.getPhone();
		logger.info(" post kechuang 科创版用户预约 ， name: "+name+",phone: "+phone);
		if(name != null && phone != null) {
			CustomerMobileEntity user = new CustomerMobileEntity();
			user.setName(name);
			user.setPhone(phone);
			customerKCService.addKCInfo(user);
			return GsonUtil.buildMap(0, "ok", null);
		}
		else {
			return GsonUtil.buildMap(1, "fail", null);
		}
		
	}
	
	@RequestMapping(value = "/saveCustomerMobile_get", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> saveCustomerMobile_get(HttpServletRequest request, @RequestParam String name, @RequestParam String phone) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" kechuang 科创版用户预约 ， name: "+name+",phone: "+phone);
		if(name != null && phone != null) {
			CustomerMobileEntity user = new CustomerMobileEntity();
			user.setName(name);
			user.setPhone(phone);
			customerKCService.addKCInfo(user);
			return GsonUtil.buildMap(0, "ok", null);
		}
		else {
			return GsonUtil.buildMap(1, "fail", null);
		}
		
	}
	
	//  end

}
