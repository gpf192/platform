package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;
import cn.xsdzq.platform.model.CheckResultDTO;
import cn.xsdzq.platform.model.InfoDTO;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.SearchBean;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.service.IInfoService;
import cn.xsdzq.platform.service.IMyInfoService;
import cn.xsdzq.platform.service.IUserService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.InfoUtil;
import cn.xsdzq.platform.util.UserManageUtil;

@Controller
@RequestMapping("/info")
public class InfoController extends BaseController {
	Logger logger = LogManager.getLogger(InfoController.class.getName());
	@Autowired
	@Qualifier("infoServiceImpl")
	private IInfoService iInfoService;

	@Autowired
	@Qualifier("myInfoServiceImpl")
	private IMyInfoService myInfoService;

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;

	@RequestMapping(value = "/getInfoById", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategory(HttpServletRequest request, @RequestParam long id) {
		logger.info("id ="+id);
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
	public Map<String, Object> getInfos(HttpServletRequest request, @RequestParam long id, @RequestParam String infoTitle,
			@RequestParam String approveResult, @RequestParam int pageNumber,@RequestParam int pageSize) {
		long categoryId = id;
		if (categoryId > 0) {
			
			User user = UserManageUtil.getUser();
			String userName = user.getUsername();
			UserEntity userEntity = userService.findUserByName(userName);
			Set<RoleEntity>  roleEntitySet = userEntity.getRoleEntities();
			boolean superFlag = false;
			//循环所有的角色，查看是否有superadmin的权限,与角色绑定，而不是与用户绑定
			for(RoleEntity role: roleEntitySet) {
				if("superadmin".equals(role.getName())) {
					superFlag = true;	
				}
			}
			CategoryEntity category = categoryService.getCategoryById(categoryId);
			String categoryTitle = category.getTitle();
			List<InfoEntity> infos = null;
			int sum = 0 ;
			if(superFlag) {
				//超级用户 ，开始判断 栏目 是否查询全部
				if("全部".equals(categoryTitle)) {				
					
					//超级用户查询全部，开始判断标题是否为空，条件：title关键字、审核状态
					if("".equals(infoTitle)) {
						//标题关键字为空，开始判断审核状态 是否为all  条件：审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：用户名
							infos = myInfoService.getInfosBySuperCreator(pageNumber, pageSize);
							sum = myInfoService.countInfosBySuperCreator();
							
						}else {
							//审核状态非all，条件：审核状态						
							infos = myInfoService.getInfosByCheckedResult(approveResult, pageNumber, pageSize);
							sum = myInfoService.countInfosByCheckedResult(approveResult);
						}

					}else {
						//标题关键字不为空，条件：title关键字、审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：title关键字	  "%"+infoTitle+"%"							
							infos = myInfoService.getInfosByTitleLike("%"+infoTitle+"%", pageNumber, pageSize);
							sum = myInfoService.countInfosByTitleLike("%"+infoTitle+"%");
						}else {
							//审核状态非all，条件：title关键字、审核状态	
							infos = myInfoService.getInfosByCheckedResultByTitleLike(approveResult, "%"+infoTitle+"%", pageNumber, pageSize);
							sum = myInfoService.countInfosByCheckedResultByTitleLike(approveResult,"%"+infoTitle+"%");
						}
					}

				}else {
					//栏目为非全部，开始判断title关键字是否为空，*********************************
					if("".equals(infoTitle)) {
						//title关键字 为空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：栏目id		
							infos = myInfoService.getInfosByCategoryIdByCheckAll(categoryId, pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryId(categoryId);
						}else {
							//审核状态为非all  ，条件：栏目id、审核状态					
							infos = myInfoService.getInfosByCategoryIdByCheckedResult(categoryId, approveResult, pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCheckedResult(categoryId, approveResult);
						}
					}else {
						//title关键字 非空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：栏目id、   title关键字
							infos = myInfoService.getInfosByCategoryIdByTitleLike(categoryId, "%"+infoTitle+"%", pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByTitleLike(categoryId, "%"+infoTitle+"%");
						}else {
							//审核状态为非all  ，条件：栏目id、title关键字  、审核状态 						
							infos = myInfoService.getInfosByCategoryIdByCheckedResultByTitleLike(categoryId, approveResult, "%"+infoTitle+"%", pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCheckedResultByTitleLike(categoryId, approveResult, "%"+infoTitle+"%");
						}
					}
					
				}
			}else {
				//普通用户,开始判断 栏目 是否查询全部
				if("全部".equals(categoryTitle)) {				
					//infos = myInfoService.getInfosByCreator(userName, pageNumber, pageSize);
					//普通用户查询全部，开始判断标题是否为空，条件：用户名、title关键字、审核状态
					if("".equals(infoTitle)) {
						//标题关键字为空，开始判断审核状态 是否为all  条件：用户名、审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：用户名
							infos = myInfoService.getInfosByCreator(userName, pageNumber, pageSize);
							sum = myInfoService.countInfosByCreator(userName);
						}else {
							//审核状态非all，条件：用户名、审核状态
							infos = myInfoService.getInfosByCreatorByCheckedResult(userName, approveResult, pageNumber, pageSize);
							sum = myInfoService.countInfosByCreatorByCheckedResult(userName, approveResult);
						}

					}else {
						//标题关键字不为空，条件：用户名、title关键字、审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：用户名、title关键字	  "%"+infoTitle+"%"
							infos = myInfoService.getInfosByCreatorByTitleLike(userName, "%"+infoTitle+"%", pageNumber, pageSize);
							sum = myInfoService.countInfosByCreatorByTitleLike(userName, "%"+infoTitle+"%");
						}else {
							//审核状态非all，条件：用户名、title关键字、审核状态
							infos = myInfoService.getInfosByCreatorByCheckedResultByTitleLike(userName, approveResult, "%"+infoTitle+"%", pageNumber, pageSize);
							sum = myInfoService.countInfosByCreatorByCheckedResultByTitleLike(userName, approveResult,"%"+infoTitle+"%");
						}

					}

				}else {
					//栏目为非全部，开始判断title关键字是否为空，*********************************
					if("".equals(infoTitle)) {
						//title关键字 为空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：用户名、栏目id
							infos = myInfoService.getInfosByCategoryIdByCreator(categoryId, userName, pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCreator(categoryId, userName);
						}else {
							//审核状态为非all  ，条件：用户名、栏目id、审核状态
							infos = myInfoService.getInfosByCategoryIdByCreatorByCheckedResult(categoryId, userName, approveResult, pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCreatorByCheckedResult(categoryId, userName, approveResult);
						}
					}else {
						//title关键字 非空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：用户名、栏目id、   title关键字
							infos = myInfoService.getInfosByCategoryIdByCreatorByTitleLike(categoryId, userName, "%"+infoTitle+"%", pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCreatorByTitleLike(categoryId, userName, "%"+infoTitle+"%");
						}else {
							//审核状态为非all  ，条件：用户名、栏目id、title关键字  、审核状态 
							infos = myInfoService.getInfosByCategoryIdByCreatorByCheckedResultByTitleLike(categoryId, userName, approveResult, "%"+infoTitle+"%", pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCreatorByCheckedResultByTitleLike(categoryId, userName, approveResult, "%"+infoTitle+"%");
						}
					}
					
				}
			}

			List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
			for (InfoEntity info : infos) {
				InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
				infoDTOs.add(dto);
			}
			//int sum = myInfoService.countInfosByCategoryId(categoryId);
			System.out.println("sum: " + sum);
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", infoDTOs, pagination);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	@RequestMapping(value = "/addInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addInfo(HttpServletRequest request, @Validated @RequestBody InfoDTO dto) {
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		// 插入创建人
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		info.setCreatedBy(name);
		iInfoService.addInfo(info);
		logger.info("action:" + "add" + ";" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletInfo(HttpServletRequest request, @RequestBody InfoDTO dto) {
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		iInfoService.deleteInfo(info);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		logger.info("action:" + "delete" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyInfo", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, @RequestBody InfoDTO dto) {
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);		
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		// 插入创建人
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		info.setCreatedBy(name);	
		info.setCreatetime(new Date());
		iInfoService.modifyInfo(info);
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/addWeight", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addWeight(@RequestBody InfoDTO dto) {
		InfoEntity infoEntity = InfoUtil.convertInfoByInfoDTO(dto);
		iInfoService.addWeight(infoEntity);
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

	// add by fjx begin
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
	public Map<String, Object> modifyCheckResult(HttpServletRequest request,
			@RequestBody CheckResultDTO checkResultDTO) {
		CheckResultDTO dto = checkResultDTO;
		String action = dto.getAction();
		System.out.println(dto.isCheckFlag() + " **** " + dto.getId());
		iInfoService.modifyCheckResult(dto.getId(), dto.isCheckFlag(),  action);
		return GsonUtil.buildMap(0, "ok", null);
	}
	//撤回信息
	@RequestMapping(value = "/revocation", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> revocation(HttpServletRequest request, @RequestBody InfoDTO dto) {
		InfoEntity info = InfoUtil.convertInfoByInfoDTO(dto);
		CategoryEntity category = categoryService.getCategoryById(info.getCategoryId());
		info.setCategoryEntity(category);
		// 插入审批人
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		info.setCreatedBy(name);
		info.setCheckedResult("generate");
		info.setModifytime(new Date());
		info.setApprovedBy(name);
		iInfoService.modifyInfo(info);	
		logger.info("action:" + "modify" + ";" + "user:" + name + ";" + "title:" + dto.getTitle() + ";");
		return GsonUtil.buildMap(0, "ok", null);
	}

	//根据分类id查询，条件：分类id 、审核状态为approve
	@RequestMapping(value = "/getInfosByCategoryIdForH5", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getInfosByCategoryIdForH5(HttpServletRequest request, @RequestParam long id) {
		List<InfoEntity> infos = myInfoService.getInfosByCategoryIdByCheckedResultForH5(id);
		List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
		for (InfoEntity info : infos) {
			InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
			infoDTOs.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", infoDTOs);
	}

	// add by fjx 审核模块用接口
	@RequestMapping(value = "/getCheckInfosByCategoryId", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getCheckInfos(HttpServletRequest request, @RequestParam long id, @RequestParam String infoTitle,
			@RequestParam String approveResult, @RequestParam int pageNumber,@RequestParam int pageSize) {
		//System.out.println("approveResult是********** ********** ********** ********** ********** **********   "  + approveResult);
		//System.out.println("infoTitle是********** ********** ********** ********** ********** **********   "  + infoTitle);
		long categoryId = id;
		if (categoryId > 0) {
			CategoryEntity category = categoryService.getCategoryById(categoryId);
			String categoryTitle = category.getTitle();
			System.out.println("categoryTitle是   "  + categoryTitle);
			List<InfoEntity> infos = null;
			int sum = 0 ;
			//开始查询所有待审核 和 已审核的 单子，与用户名无关
				//开始判断 栏目 是否查询全部
				if("全部".equals(categoryTitle)) {				
					
					//查询全部，开始判断标题是否为空，条件：title关键字、审核状态
					if("".equals(infoTitle)) {
						//标题关键字为空，开始判断审核状态 是否为all  条件：审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：审核状态为 已审核approve、待审核 submit  
							infos = myInfoService.getCheckInfosBySuperCreator(pageNumber, pageSize); 
							sum = myInfoService.countCheckInfosBySuperCreator();
							
						}else {
							//审核状态非all，条件：审核状态						
							infos = myInfoService.getInfosByCheckedResult(approveResult, pageNumber, pageSize);
							sum = myInfoService.countInfosByCheckedResult(approveResult);
						}

					}else {
						//标题关键字不为空，条件：title关键字、审核状态
						if("all".equals(approveResult)){
							//审核状态为all，条件：title关键字	  "%"+infoTitle+"%"							
							infos = myInfoService.getCheckInfosByTitleLike("%"+infoTitle+"%", pageNumber, pageSize); 
							sum = myInfoService.countCheckInfosByTitleLike("%"+infoTitle+"%");
						}else {
							//审核状态非all，条件：title关键字、审核状态	
							infos = myInfoService.getInfosByCheckedResultByTitleLike(approveResult, "%"+infoTitle+"%", pageNumber, pageSize);
							sum = myInfoService.countInfosByCheckedResultByTitleLike(approveResult,"%"+infoTitle+"%");
						}
					}

				}else {
					//栏目为非全部，开始判断title关键字是否为空，
					if("".equals(infoTitle)) {
						//title关键字 为空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：栏目id		Check
							infos = myInfoService.getCheckInfosByCategoryIdByCheckAll(categoryId, pageNumber,pageSize);
							sum = myInfoService.countCheckInfosByCategoryId(categoryId);
						}else {
							//审核状态为非all  ，条件：栏目id、审核状态					
							infos = myInfoService.getInfosByCategoryIdByCheckedResult(categoryId, approveResult, pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCheckedResult(categoryId, approveResult);
						}
					}else {
						//title关键字 非空，开始判断审核状态是否为全部
						if("all".equals(approveResult)) {
							//审核状态为 all， 条件：栏目id、   title关键字
							infos = myInfoService.getCheckInfosByCategoryIdByTitleLike(categoryId, "%"+infoTitle+"%", pageNumber,pageSize);
							sum = myInfoService.countCheckInfosByCategoryIdByTitleLike(categoryId, "%"+infoTitle+"%");
						}else {
							//审核状态为非all  ，条件：栏目id、title关键字  、审核状态 						
							infos = myInfoService.getInfosByCategoryIdByCheckedResultByTitleLike(categoryId, approveResult, "%"+infoTitle+"%", pageNumber,
									pageSize);
							sum = myInfoService.countInfosByCategoryIdByCheckedResultByTitleLike(categoryId, approveResult, "%"+infoTitle+"%");
						}
					}					
				}
			

			List<InfoDTO> infoDTOs = new ArrayList<InfoDTO>();
			for (InfoEntity info : infos) {
				InfoDTO dto = InfoUtil.convertInfoDTOByInfo(info);
				infoDTOs.add(dto);			
			}
			//int sum = myInfoService.countInfosByCategoryId(categoryId);
			System.out.println("sum: " + sum);
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", infoDTOs, pagination);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}
}
