package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.SearchBean;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;

@Controller
@RequestMapping("/front")
public class FrontController {

	@Autowired
	@Qualifier("infoServiceImpl")
	private IInfoService iInfoService;

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;

	@RequestMapping(value = "/getCategories", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllCategory() {
		List<CategoryEntity> list = categoryService.findAll();
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
	public Map<String, Object> getInfos(HttpServletRequest request, @RequestParam long id) {
		System.out.println(id + "********");
		long categoryId = id;
		if (categoryId > 0) {
			List<InfoEntity> infos = iInfoService.getInfosByCategoryIdToFront(categoryId);
			List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
			for (InfoEntity info : infos) {
				InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
				infoDTOs.add(dto);
			}
			return GsonUtil.buildMap(0, "ok", infoDTOs);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	@RequestMapping(value = "/search", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest request, @RequestBody SearchBean searchBean) {
		System.out.println(searchBean.getKey());
		String key = searchBean.getKey();
		List<InfoEntity> infos = iInfoService.searchInfos(key);
		List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
		for (InfoEntity info : infos) {
			InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
			infoDTOs.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}

}
