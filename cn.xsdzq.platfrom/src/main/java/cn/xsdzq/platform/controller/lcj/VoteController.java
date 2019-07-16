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
import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.EmpVoteEntity;
import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteForDTO;
import cn.xsdzq.platform.service.lcj.EmpVoteService;
import cn.xsdzq.platform.service.lcj.MyEmpVoteService;
import cn.xsdzq.platform.service.lcj.MyUserVoteForService;
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
	@Qualifier("myUserVoteForServiceImpl")
	private MyUserVoteForService myUserVoteForService;
	
	
	@Autowired
	@Qualifier("myEmpVoteServiceImpl")
	private MyEmpVoteService myEmpVoteService;
	
	@Autowired
	@Qualifier("empVoteServiceImpl")
	private EmpVoteService empVoteService;
	
	@RequestMapping(value = "/getUserVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUserVote(HttpServletRequest request,  
			@RequestParam String username, @RequestParam String clientId, @RequestParam String sourceId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户投票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
		int sum = 0 ;
		List<UserTicketRecordEntity> entitys = null;
		int num = MethodUtil.getUserVoteMethodNum(username, clientId, sourceId);
		if(num == 1) {
			//全量查找
			entitys = myUserVoteService.getAll(pageNumber, pageSize);
			sum = myUserVoteService.countAll();
		}
		if(num == 2) {
			//三个条件一起查询
			entitys = myUserVoteService.findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceGainTime(username, clientId, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(username, clientId, sourceId);
		}
		if(num == 3) {
			//查询条件：username
			entitys = myUserVoteService.findByUserEntity_usernameGainTime(username, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_username(username);
		}
		if(num == 4) {
			//查询条件：account
			entitys = myUserVoteService.findByUserEntity_clientIdGainTime(clientId, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_clientId(clientId);
		}
		if(num == 5) {
			//查询条件：surceId
			entitys = myUserVoteService.findByVotesSourceGainTime(sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByVotesSource(sourceId);
		}
		if(num == 6) {
			//查询条件：username \account
			entitys = myUserVoteService.findByUserEntity_usernameAndUserEntity_clientIdGainTime(username, clientId, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
		}
		if(num == 7) {
			////查询条件：username \surceId
			entitys = myUserVoteService.findByUserEntity_usernameAndVotesSourceGainTime(username, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_usernameAndVotesSource(username, sourceId);
		}
		if(num == 8) {
			//查询条件：account\ surceId
			entitys = myUserVoteService.findByUserEntity_clientIdAndVotesSourceGainTime(clientId, sourceId, pageNumber, pageSize);
			sum = myUserVoteService.countByUserEntity_clientIdAndVotesSource(clientId, sourceId);
		}
		
					
		List<UserVoteDTO> DTOs = new ArrayList<UserVoteDTO>();
		for (UserTicketRecordEntity entity : entitys) {
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
			@RequestParam String empName, @RequestParam String empCode,@RequestParam String division,
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量参赛人员得票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		//String voteFromUser = vote_from_user;
		int sum = 0 ;
		List<EmpTicketRecordEntity> entitys = null;
		int num = MethodUtil.getEmpVoteMethodNum(empName, empCode, division);
		if(num == 1) {
			entitys = myEmpVoteService.getAll(pageNumber, pageSize);
			sum = myEmpVoteService.countAll();
		}
		if(num == 2) {
			// 查询条件 empName、empCode\division
			entitys = myEmpVoteService.findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(empName, empCode, division, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(empName, empCode, division);
		}
		if(num == 3) {
			//查询条件 empName
			entitys = myEmpVoteService.findByEmpEntity_empNameOrderByRecordTimeDesc(empName, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empName(empName);
		}
		if(num == 4) {
			//查询条件 empCode
			entitys = myEmpVoteService.findByEmpEntity_empCodeOrderByRecordTimeDesc(empCode, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empCode(empCode);
		}	
		if(num == 5) {
			// 查询条件 division
			entitys = myEmpVoteService.findByEmpEntity_divisionOrderByRecordTimeDesc(division, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_division( division);
		}
		if(num == 6) {
			//查询条件 empName、empCode
			entitys = myEmpVoteService.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTimeDesc(empName, empCode, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empNameAndEmpEntity_empCode(empName, empCode);
		}
		if(num == 7) {
			//查询条件 empName、division
			entitys = myEmpVoteService.findByEmpEntity_empNameAndEmpEntity_divisionOrderByRecordTimeDesc(empName, division, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empNameAndEmpEntity_division(empName, division);
		}
		if(num == 8) {
			//查询条件 empCode、division
			entitys = myEmpVoteService.findByEmpEntity_empCodeAndEmpEntity_divisionOrderByRecordTimeDesc(empCode, division, pageNumber, pageSize);
			sum = myEmpVoteService.countByEmpEntity_empCodeAndEmpEntity_division(empCode, division);
		}
		List<EmpVoteDTO> DTOs = new ArrayList<EmpVoteDTO>();
		for (EmpTicketRecordEntity entity : entitys) {
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
	
	//获取用户投票信息
		@RequestMapping(value = "/getUserVoteFor", method = GET, produces = "application/json; charset=utf-8")
		@ResponseBody
		public Map<String, Object> getUserVoteFor(HttpServletRequest request,  
				@RequestParam String username, @RequestParam String clientId,
				@RequestParam String empName, @RequestParam String empCode,
				 @RequestParam int pageNumber,@RequestParam int pageSize) {
			System.out.println("用户投票数信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(username+"-"+clientId);
			int sum = 0 ;
			List<UserVoteEmpResultEntity> entitys = null;
			int num = MethodUtil.getUserVoteForMethodNum(username, clientId, empName,empCode);
			if(num == 1) {
				//全量查找
				System.out.println("into   1 ____");
				entitys = myUserVoteForService.getAll(pageNumber, pageSize);
				sum = myUserVoteForService.countAll();
			}
			if(num == 2) {
				//4个条件一起查询
				System.out.println("into   2 ____");
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(username, clientId, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(username, clientId, empName, empCode);
			}
			if(num == 3) {
				//查询条件：username\clientId\empName\
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(username, clientId, empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empName(username, clientId, empName);
			}
			if(num == 4) {
				//查询条件：username\clientId\\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(username, clientId, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndUserEntity_empCode(username, clientId, empCode);
			}
			if(num == 5) {
				//查询条件：username\\empName\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime(username, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_empNameAndUserEntity_empCode(username, empName, empCode);
			}
			if(num == 6) {
				//查询条件：\clientId\empName\empCode
				entitys = myUserVoteForService.findByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( clientId, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndUserEntity_empNameAndUserEntity_empCode(clientId, empName, empCode);
			}
			if(num == 7) {
				////查询条件：username\clientId\\
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(username, clientId, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
			}
			if(num == 8) {
				//查询条件：username\\empName\
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_empNameOrderByRecordTime(username, empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_empName(username, empName);
			}
			if(num == 9) {
				//username\\\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_empCodeOrderByRecordTime(username, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_empCode(username, empCode);
			}
			if(num == 10) {
				//查询条件：\clientId\empName\
				entitys = myUserVoteForService.findByUserEntity_clientIdAndUserEntity_empNameOrderByRecordTime(clientId, empName,pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndUserEntity_empName( clientId, empName );
			}
			if(num == 11) {
				//查询条件：\clientId\\empCode
				entitys = myUserVoteForService.findByUserEntity_clientIdAndUserEntity_empCodeOrderByRecordTime(clientId,  empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndUserEntity_empCode(clientId,  empCode);
			}
			if(num == 12) {
				//查询条件：\\empName\empCode
				entitys = myUserVoteForService.findByUserEntity_empNameAndUserEntity_empCodeOrderByRecordTime( empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_empNameAndUserEntity_empCode(empName, empCode);
			}
			if(num == 13) {
				//查询条件：username 
				entitys = myUserVoteForService.findByUserEntity_usernameOrderByRecordTime(username, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_username(username);
			}
			if(num == 14) {
				////查询条件： \clientId
				entitys = myUserVoteForService.findByUserEntity_clientIdOrderByRecordTime(clientId, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientId(clientId);
			}
			if(num == 15) {
				//查询条件：empName
				entitys = myUserVoteForService.findByUserEntity_empNameOrderByRecordTime(empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_empName(empName);
			}
			if(num == 16) {
				//查询条件：empCode
				entitys = myUserVoteForService.findByUserEntity_empCodeOrderByRecordTime(empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_empCode( empCode);
			}
			
						
			List<UserVoteForDTO> DTOs = new ArrayList<UserVoteForDTO>();
			for (UserVoteEmpResultEntity entity : entitys) {
				UserVoteForDTO dto = LcjUtil.convertUserVoteForDTOByEntity(entity);
				DTOs.add(dto);
			}
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", DTOs, pagination);
			}
}
