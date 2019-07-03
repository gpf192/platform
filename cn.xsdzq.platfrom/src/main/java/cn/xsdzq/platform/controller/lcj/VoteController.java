package cn.xsdzq.platform.controller.lcj;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
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

import cn.xsdzq.platform.controller.BaseController;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteDTO;
import cn.xsdzq.platform.service.lcj.EmpVoteService;
import cn.xsdzq.platform.service.lcj.MyEmpVoteService;
import cn.xsdzq.platform.service.lcj.MyUserVoteService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.LcjUtil;
import cn.xsdzq.platform.util.MethodUtil;

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
	
	@Autowired
	@Qualifier("empVoteServiceImpl")
	private EmpVoteService empVoteService;
	
	@RequestMapping(value = "/getUserVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUserVote(HttpServletRequest request,  
			@RequestParam String username, @RequestParam String account, @RequestParam String sourceId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户投票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
		int sum = 0 ;
		List<UserVoteEntity> entitys = null;
		int num = MethodUtil.getUserVoteMethodNum(username, account, sourceId);
		if(num == 1) {
			//全量查找
			entitys = myUserVoteService.getAll(pageNumber, pageSize);
			sum = myUserVoteService.countAll();
		}
		if(num == 2) {
			//三个条件一起查询
			entitys = myUserVoteService.findByUsernameAndAccountAndSourceIdOrderByAccount(username, account, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByUsernameAndAccountAndSourceId(username, account, sourceId);
		}
		if(num == 3) {
			//查询条件：username
			entitys = myUserVoteService.findByUsernameOrderByAccount(username, pageNumber, pageSize);
			sum = myUserVoteService.countByUsername(username);
		}
		if(num == 4) {
			//查询条件：account
			entitys = myUserVoteService.findByAccountOrderByAccount(account, pageNumber, pageSize);
			sum = myUserVoteService.countByAccount(account);
		}
		if(num == 5) {
			//查询条件：surceId
			entitys = myUserVoteService.findBySourceIdOrderByAccount(sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countBySourceId(sourceId);
		}
		if(num == 6) {
			//查询条件：username \account
			entitys = myUserVoteService.findByUsernameAndAccountOrderByAccount(username, account, pageNumber, pageSize);
			sum = myUserVoteService.countByUsernameAndAccount(username, account);
		}
		if(num == 7) {
			////查询条件：username \surceId
			entitys = myUserVoteService.findByUsernameAndSourceIdOrderByAccount(username, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByUsernameAndSourceId(username, sourceId);
		}
		if(num == 8) {
			//查询条件：account\ surceId
			entitys = myUserVoteService.findByAccountAndSourceIdOrderByAccount(account, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByAccountAndSourceId(account, sourceId);
		}
		
					
		List<UserVoteDTO> DTOs = new ArrayList<UserVoteDTO>();
		for (UserVoteEntity entity : entitys) {
			UserVoteDTO dto = LcjUtil.convertUserVoteDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
	//获取参赛人员得票信息
	@RequestMapping(value = "/getEmpVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getEmpVote(HttpServletRequest request,  
			@RequestParam String empName, @RequestParam String vote_from_user,
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量参赛人员得票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		String voteFromUser = vote_from_user;
		int sum = 0 ;
		List<EmpVoteEntity> entitys = null;
		int num = MethodUtil.getEmpVoteMethodNum(empName,voteFromUser);
		if(num == 1) {
			entitys = myEmpVoteService.getAll(pageNumber, pageSize);
			sum = myEmpVoteService.countAll();
		}
		if(num == 2) {
			// 查询条件 empName、sourceUser
			entitys = myEmpVoteService.findByEmpNameAndVoteFromUserOrderByWeightDesc(empName, voteFromUser, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpNameAndVoteFromUser(empName, voteFromUser);
		}
		if(num == 3) {
			//查询条件 empName
			entitys = myEmpVoteService.findByEmpNameOrderByWeightDesc(empName, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpName(empName);
		}
		if(num == 4) {
			//查询条件 sourceUser
			entitys = myEmpVoteService.findByVoteFromUserOrderByWeightDesc(voteFromUser, pageNumber, pageSize);
			sum = myEmpVoteService.countByVoteFromUser(voteFromUser);
		}
					
		List<EmpVoteDTO> DTOs = new ArrayList<EmpVoteDTO>();
		for (EmpVoteEntity entity : entitys) {
			EmpVoteDTO dto = LcjUtil.convertEmpVoteDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
	
	@RequestMapping(value = "/addWeight", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addWeight(@RequestBody EmpVoteDTO dto) {
		EmpVoteEntity entity = LcjUtil.convertEmpVoteEntityByDTO(dto);
		empVoteService.addWeight(entity);
		return GsonUtil.buildMap(0, "ok", null);
	}
}
