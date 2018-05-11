package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.CheckResultDTO;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.SearchBean;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;

@Controller
@RequestMapping("/info")
public class InfoController extends BaseController {
	@Autowired
	@Qualifier("infoServiceImpl")
	private IInfoService iInfoService;

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;

	@RequestMapping(value = "/getInfoById/{param}", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategory(HttpServletRequest request, @PathVariable String param) {
		long id = Long.parseLong(param);
		if (id > 0) {
			InfoEntity info = iInfoService.getInfo(id);
			InfoDTO infoDTO = InfoUtil.convertInfoDTOByInfo(info);
			return GsonUtil.buildMap(0, "ok", infoDTO);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	@RequestMapping(value = "/getInfosByCategoryId", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getInfos(HttpServletRequest request, @RequestParam long id) {
	//public Map<String, Object> getInfos(HttpServletRequest request, @PathVariable(value="param") String param) {
		System.out.println(id+"********");
		long categoryId = id;
		if (categoryId > 0) {
			List<InfoEntity> infos = iInfoService.getInfosByCategoryId(categoryId);
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

	@RequestMapping(value = "/addInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addInfo(HttpServletRequest request, @RequestBody InfoDTO dto) {
		System.out.println(dto.getTitle());
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		//插入创建人
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		User user = (User) auth.getPrincipal();
		String  name = user.getUsername();
		info.setCreated_by(name);		
		iInfoService.addInfo(info);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletInfo(HttpServletRequest request, @RequestBody InfoDTO dto) {
		System.out.println(dto.getTitle()+"****** 删除信息");
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		iInfoService.deleteInfo(info);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, @RequestBody InfoDTO dto) {
		System.out.println(dto.getTitle()+"********************");
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		//插入创建人
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		User user = (User) auth.getPrincipal();
		String  name = user.getUsername();
		info.setCreated_by(name);	
		iInfoService.modifyInfo(info);
		return GsonUtil.buildMap(0, "ok", null);
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
	//add by fjx begin
	/**
	 * 获取未审核的流程 接口1
	 */
	@RequestMapping(value = "/getUncheckedInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUncheckedInfo(HttpServletRequest request) {
		List<InfoEntity> infos = iInfoService.searUncheckchInfos();
		List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
		for (InfoEntity info : infos) {
			InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
			infoDTOs.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);

	}	
	/**
	 * 插入审核结果 接口
	 */
	@RequestMapping(value = "/modifyCheckResult", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyCheckResult(HttpServletRequest request, @RequestBody CheckResultDTO checkResultDTO) {	
		CheckResultDTO dto = checkResultDTO;
		System.out.println(dto.isCheckFlag()+" **** "+dto.getId());
		iInfoService.modifyCheckResult(dto.getId(), dto.isCheckFlag());
		return GsonUtil.buildMap(0, "ok", null);
	}
	
	//add by fjx 

}
