package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.xsdzq.platform.entity.lcj.DepartmentEntity;
import cn.xsdzq.platform.model.lcj.DepartmentDTO;
import cn.xsdzq.platform.service.lcj.DepartmentService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	private static Logger logger = LogManager.getLogger(DepartmentController.class);

	@Autowired
	@Qualifier("departmentServiceImpl")
	private DepartmentService departmentService;
	
/*	@RequestMapping(value = "/getDepartmentById", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public DepartmentDTO getDepartmentById() {
		DepartmentEntity entity = departmentService.findDepartmentById(1);
		
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}*/

	@RequestMapping(value = "/getAll", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getAll() {
		List<DepartmentEntity> list = departmentService.findAll();
		List<DepartmentDTO> cDtos = new ArrayList<DepartmentDTO>();
		for (DepartmentEntity entity : list) {
			DepartmentDTO dto = LcjUtil.convertDepartmentDTOByEntity(entity);
			cDtos.add(dto);
		}
		cDtos.sort(Comparator.naturalOrder());
		return GsonUtil.buildMap(0, "ok", cDtos);
	}
}
