package cn.xsdzq.platform.controller.lcj;

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
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpDTO;
import cn.xsdzq.platform.model.lcj.EmpVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteDTO;
import cn.xsdzq.platform.service.lcj.EmpService;
import cn.xsdzq.platform.service.lcj.MyEmpService;
import cn.xsdzq.platform.service.lcj.MyEmpVoteService;
import cn.xsdzq.platform.service.lcj.MyUserVoteService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;

@Controller
@RequestMapping("/vote")
public class VoteController extends BaseController {
	Logger logger = LogManager.getLogger(VoteController.class.getName());
	
	
	@Autowired
	@Qualifier("myUserVoteServiceImpl")
	private MyUserVoteService myUserVoteService;
	
	@Autowired
	@Qualifier("myEmpVoteServiceImpl")
	private MyEmpVoteService myEmpVoteService;
	
	@RequestMapping(value = "/getUserVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUserVote(HttpServletRequest request,  
//			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户投票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		/*Date endDate = null;
		Date beginDate = null;*/
		
		int sum = 0 ;
		List<UserVoteEntity> entitys = null;
		//int num = MethodUtil.getMethodNum(beginTime,endTime);
		
			entitys = myUserVoteService.getAll(pageNumber, pageSize);
			sum = myUserVoteService.countAll();
		
	
					
		List<UserVoteDTO> DTOs = new ArrayList<UserVoteDTO>();
		for (UserVoteEntity entity : entitys) {
			UserVoteDTO dto = LcjUtil.convertUserVoteDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
	//获取参赛人员信息
	@RequestMapping(value = "/getEmpVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getEmpVote(HttpServletRequest request,  
//			@RequestParam String beginTime, @RequestParam String endTime, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量参赛人员得票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		/*Date endDate = null;
		Date beginDate = null;*/
		
		int sum = 0 ;
		List<EmpVoteEntity> entitys = null;
		//int num = MethodUtil.getMethodNum(beginTime,endTime);
		
			entitys = myEmpVoteService.getAll(pageNumber, pageSize);
			sum = myEmpVoteService.countAll();
		
	
					
		List<EmpVoteDTO> DTOs = new ArrayList<EmpVoteDTO>();
		for (EmpVoteEntity entity : entitys) {
			EmpVoteDTO dto = LcjUtil.convertEmpVoteDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
}
