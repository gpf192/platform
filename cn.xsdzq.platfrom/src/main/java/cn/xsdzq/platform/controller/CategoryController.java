package cn.xsdzq.platform.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.logging.Logger;

import cn.xsdzq.platform.entity.CategoryEntity;
import cn.xsdzq.platform.model.CategoryDTO;
import cn.xsdzq.platform.model.IdDTO;
import cn.xsdzq.platform.service.ICategoryService;
import cn.xsdzq.platform.util.CategoryUtil;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.PhotoUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private static Logger logger = Logger.getLogger(CategoryController.class);

	@Autowired
	@Qualifier("categoryServiceImpl")
	private ICategoryService categoryService;

	@RequestMapping(value = "/getCategory", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public CategoryDTO getCategory() {
		CategoryEntity category = categoryService.getCategoryById(1);
		System.out.println(category.getTitle());
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setTitle(category.getTitle());
		return categoryDTO;
	}

	@RequestMapping(value = "/getAll", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAllCategory() {
		List<CategoryEntity> list = categoryService.findAll();
		List<CategoryDTO> cDtos = new ArrayList<CategoryDTO>();
		for (CategoryEntity category : list) {
			CategoryDTO dto = CategoryUtil.convertCategoryDTOByCategoryEntity(category);
			cDtos.add(dto);
		}
		return GsonUtil.buildMap(0, "ok", cDtos);
	}

	@RequestMapping(value = "/addCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addCategory(HttpServletRequest request, @RequestBody CategoryDTO categoryDTO) {
		System.out.println(categoryDTO.getTitle());
		CategoryEntity category = CategoryUtil.convertCategoryEntityByCategoryDTO(categoryDTO);
		// String title = dto.getTitle();
		// category.setTitle(title);
		categoryService.addCategory(category);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/modifyCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> modifyCategory(HttpServletRequest request, @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO dto = categoryDTO;
		CategoryEntity category = new CategoryEntity();
		category.setId(dto.getId());
		category.setTitle(dto.getTitle());
		category.setExp(dto.getExp());
		System.out.println(dto.getTitle());
		categoryService.modifyCategory(category);
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/deleteCategory", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteCategory(@RequestBody IdDTO idDTO) {
		long id = idDTO.getId();
		logger.info("id: " + id);
		if (id > 0) {
			categoryService.deleteCategory(id);
			return GsonUtil.buildMap(0, "ok", null);
		} else {
			return GsonUtil.buildMap(1, "fail", null);
		}
	}

	@RequestMapping(value = "/sort", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> sort(@RequestBody List<CategoryDTO> categoryDTOs) {
		return GsonUtil.buildMap(0, "ok", null);
	}

	@ResponseBody
	@RequestMapping(value = "upload", method = POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		System.out.println("进入上传页面     ");

		// 第一种返回页面的方法
		// model.addAttribute("img",PhotoUtil.saveFile(file,request));
		// 第二种返回页面的方法
		request.setAttribute("img", PhotoUtil.saveFile(file, request));
		return GsonUtil.buildMap(0, "ok", null);
	}

	@RequestMapping(value = "/add", method = POST)
	public ResponseEntity<Object> addProduct(@RequestParam("file") MultipartFile uploadFiles) {
		System.out.println("进入上传页面     ");
		String fileName = uploadFiles.getOriginalFilename();
		String prefix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		File dst = null;
		try {
			String root = System.getProperty("catalina.base"); // 获取tomcat根路径
			File uploadDir = new File(root, "webapps/upload"); // 创建一个指向tomcat/webapps/upload目录的对象
			if (!uploadDir.exists()) {
				uploadDir.mkdir(); // 如果不存在则创建upload目录
			}
			dst = new File(uploadDir, UUID.randomUUID().toString() + prefix); // 创建一个指向upload目录下的文件对象，文件名随机生成
			uploadFiles.transferTo(dst); // 创建文件并将上传文件复制过去
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 然后把路径set到productVo中 完成添加 "/upload/"+dst.getName();
		return null;

	}
}
