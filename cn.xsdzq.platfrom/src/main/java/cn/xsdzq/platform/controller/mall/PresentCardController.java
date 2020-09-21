package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.entity.mall.PresentResultEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
import cn.xsdzq.platform.model.mall.PresentResultDTO;
import cn.xsdzq.platform.service.mall.PagePresentCardService;
import cn.xsdzq.platform.service.mall.PresentCardService;
import cn.xsdzq.platform.service.mall.PresentResultService;
import cn.xsdzq.platform.service.mall.PresentService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.UserManageUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;


@RestController
@RequestMapping(value = "/mall/card")
public class PresentCardController {
	@Autowired
	private PresentCardService presentCardService;

	@Autowired
	@Qualifier("presentServiceImpl")
	private PresentService presentService;
	
	@Autowired
	@Qualifier("pagePresentCardServiceImpl")
	private PagePresentCardService pagePresentCardService;
	
	@Autowired
	@Qualifier("presentResultServiceImpl")
	private PresentResultService presentResultService;
	
	@PostMapping(value = "/add")
	public Map<String, Object> addCard(@RequestBody PresentCardDTO dto) {
		System.out.println(dto.toString());
		PresentCardEntity entity = PresentUtil.convertPresentCardEntityByDTO(dto);
		PresentEntity p = presentService.findById(dto.getPresentId());
		entity.setPresent(p);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		if(dto.getIsNew()==0) {
			//新增
			entity.setCreatedBy(name);
			presentCardService.addPresentCard(entity);
			
		}else {
			PresentCardEntity temp = presentCardService.findById(dto.getId());
			entity.setCreateDate(temp.getCreateDate());//保持不变
			entity.setModifytime(new Date());
			entity.setCreatedBy(temp.getCreatedBy());//保持不变
			entity.setModifyBy(name);
			System.out.println(entity.toString());
			presentCardService.addPresentCard(entity);
		}
		
		return GsonUtil.buildMap(0, "success", null);
	}

	@GetMapping(value = "/all")
	public Map<String, Object> getAllCards(HttpServletRequest request,@RequestParam String cardId,@RequestParam String presentId, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		int num = MethodUtil.getMethodNum(cardId, presentId);
		List<PresentCardEntity> entities = null;
		
		if(num == 1) {
			//全量查找
			entities = pagePresentCardService.findByOrderByCreateDateDesc(pageNumber, pageSize);
			sum = pagePresentCardService.countAll();
		}
		if(num == 2) {
			//ab查找
			long pId = Long.parseLong(presentId);
			entities = pagePresentCardService.findByCardIdLikeAndPresentIdOrderByCreateDateDesc(cardId, pId, pageNumber, pageSize);
			sum = pagePresentCardService.countByCardIdLikeAndPresentId(cardId, pId);
		}
		if(num == 3) {
			//a查找
			entities = pagePresentCardService.findByCardIdLikeOrderByCreateDateDesc(cardId, pageNumber, pageSize);
			sum = pagePresentCardService.countByCardIdLike(cardId);
		}
		if(num == 4) {
			long pId = Long.parseLong(presentId);
			//b查找
			entities = pagePresentCardService.findByPresentIdOrderByCreateDateDesc(pId, pageNumber, pageSize);
			sum = pagePresentCardService.countByPresentId(pId);
		}
		
		
		List<PresentCardDTO> dtos = new ArrayList<PresentCardDTO>();
		for (PresentCardEntity entity : entities) {
			PresentCardDTO dto = PresentUtil.convertPresentCardDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}

	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePresentCard(HttpServletRequest request,  @RequestBody PresentCardDTO dto) {		
		presentCardService.deletePresentCard(dto);
		
		return GsonUtil.buildMap(0, "success", null);
	}
	
	//兑换记录
	@GetMapping(value = "/exchangeRecords")
	public Map<String, Object> getPresentResult(HttpServletRequest request,@RequestParam String presentId,@RequestParam String clientId,
			@RequestParam String mobile,@RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("jiru"+presentId+clientId+clientId);
		int sum = 0 ;
		int num = MethodUtil.getEmpMethodNum(presentId,clientId, clientId);
		
		List<PresentResultEntity> entities = null;
		long pid = 0;
		if(!"".equals(presentId)) {
			pid = Long.parseLong(presentId);
		}
		if(num == 1) {
			entities = presentResultService.findByOrderByRecordTimeDesc(pageNumber, pageSize);
			sum = presentResultService.countAll();
			System.out.println(entities.size()+"--"+sum);
		}
		if(num == 2) {
			entities = presentResultService.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(pid, clientId, mobile, pageNumber, pageSize);
			sum = presentResultService.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(pid, clientId, mobile);
		}
		if(num == 3) {
			entities = presentResultService.findByPresentCardEntity_presentIdOrderByRecordTimeDesc(pid, pageNumber, pageSize);
			sum = presentResultService.countByPresentCardEntity_presentId(pid);
		}
		if(num == 4) {
			entities = presentResultService.findByMallUserEntity_clientIdLikeOrderByRecordTimeDesc(clientId, pageNumber, pageSize);
			sum = presentResultService.countByMallUserEntity_clientIdLike(clientId);
		}
		if(num == 5) {
			entities = presentResultService.findByMallUserEntity_mobileLikeOrderByRecordTimeDesc(mobile, pageNumber, pageSize);
			sum = presentResultService.countByMallUserEntity_mobileLike(mobile);
		}
		if(num == 6) {
			entities = presentResultService.findByPresentCardEntity_presentIdAndMallUserEntity_clientIdLikeOrderByRecordTimeDesc(pid, clientId, pageNumber, pageSize);
			sum = presentResultService.countByPresentCardEntity_presentIdAndMallUserEntity_clientIdLike(pid, clientId);
		}
		if(num == 7) {
			entities = presentResultService.findByPresentCardEntity_presentIdAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(pid, mobile, pageNumber, pageSize);
			sum = presentResultService.countByPresentCardEntity_presentIdAndMallUserEntity_mobileLike(pid, mobile);
		}
		if(num == 8) {
			entities = presentResultService.findByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLikeOrderByRecordTimeDesc(clientId, mobile, pageNumber, pageSize);
			sum = presentResultService.countByMallUserEntity_clientIdLikeAndMallUserEntity_mobileLike(clientId, mobile);
		}
		List<PresentResultDTO> dtos = new ArrayList<PresentResultDTO>();
		for (PresentResultEntity entity : entities) {
			PresentResultDTO dto = PresentUtil.convertDTOByPresentResultEntity(entity);
			System.out.println(dto.toString());
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
}
