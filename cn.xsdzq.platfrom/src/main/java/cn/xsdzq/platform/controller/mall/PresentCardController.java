package cn.xsdzq.platform.controller.mall;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.service.mall.PresentCardService;
import cn.xsdzq.platform.util.GsonUtil;


@RestController
@RequestMapping(value = "/mall/card")
public class PresentCardController {
	@Autowired
	private PresentCardService presentCardService;

	@PostMapping(value = "/add")
	public Map<String, Object> addCard(@RequestBody PresentCardEntity presentCardEntity) {

		presentCardService.addPresentCard(presentCardEntity);
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getPresentCategorys() {

		List<PresentCardEntity> presentCardEntities = presentCardService.getPresentCardEntities();
		return GsonUtil.buildMap(0, "success", presentCardEntities);
	}

}
