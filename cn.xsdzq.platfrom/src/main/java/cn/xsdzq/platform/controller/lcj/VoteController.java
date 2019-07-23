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
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.UserTicketRecordEntity;
import cn.xsdzq.platform.entity.lcj.UserVoteEmpResultEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.lcj.EmpVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteDTO;
import cn.xsdzq.platform.model.lcj.UserVoteForDTO;
import cn.xsdzq.platform.service.lcj.EmpVoteService;
import cn.xsdzq.platform.service.lcj.MyEmpTicketService;
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
	
	@Autowired
	@Qualifier("myEmpTicketServiceImpl")
	private MyEmpTicketService myEmpTicketService;
	
	@RequestMapping(value = "/getUserVote", method = GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getUserVote(HttpServletRequest request,  
			@RequestParam String username, @RequestParam String clientId, @RequestParam String sourceId, 
			 @RequestParam int pageNumber,@RequestParam int pageSize) {
		System.out.println("全量查询用户得票记录信息   +   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
		int sum = 0 ;
		List<UserTicketRecordEntity> entitys = null;
		int num = MethodUtil.getUserVoteMethodNum(username, clientId, sourceId);
		try {
			if(num == 1) {
				//全量查找
				entitys = myUserVoteService.getAll(pageNumber, pageSize);
				sum = myUserVoteService.countAll();
			}
			if(num == 2) {
				//三个条件一起查询
				entitys = myUserVoteService.findByUserEntity_usernameAndUserEntity_clientIdAndVotesSourceOrderByGainTime(username, clientId, sourceId, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_usernameAndUserEntity_clientIdAndVotesSource(username, clientId, sourceId);
			}
			if(num == 3) {
				//查询条件：username
				entitys = myUserVoteService.findByUserEntity_usernameOrderByGainTime(username, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_username(username);
			}
			if(num == 4) {
				//查询条件：account
				entitys = myUserVoteService.findByUserEntity_clientIdOrderByGainTime(clientId, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_clientId(clientId);
			}
			if(num == 5) {
				//查询条件：surceId
				entitys = myUserVoteService.findByVotesSourceOrderByGainTime(sourceId, pageNumber, pageSize);
				sum = myUserVoteService.countByVotesSource(sourceId);
			}
			if(num == 6) {
				//查询条件：username \account
				entitys = myUserVoteService.findByUserEntity_usernameAndUserEntity_clientIdOrderByGainTime(username, clientId, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
			}
			if(num == 7) {
				////查询条件：username \surceId
				entitys = myUserVoteService.findByUserEntity_usernameAndVotesSourceOrderByGainTime(username, sourceId, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_usernameAndVotesSource(username, sourceId);
			}
			if(num == 8) {
				//查询条件：account\ surceId
				entitys = myUserVoteService.findByUserEntity_clientIdAndVotesSourceOrderByGainTime(clientId, sourceId, pageNumber, pageSize);
				sum = myUserVoteService.countByUserEntity_clientIdAndVotesSource(clientId, sourceId);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
					
		List<UserVoteDTO> DTOs = new ArrayList<UserVoteDTO>();
		for (UserTicketRecordEntity entity : entitys) {
			UserVoteDTO dto = null;
			try {
				dto = LcjUtil.convertUserVoteDTOByEntity(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		List<EmpTicketEntity> entitys = null;
		int num = MethodUtil.getEmpVoteMethodNum(empName, empCode, division);
		if(num == 1) {
			entitys = myEmpTicketService.getAll(pageNumber, pageSize);
			sum = myEmpTicketService.countAll();
		}
		if(num == 2) {
			// 查询条件 empName、empCode\division
			entitys = myEmpTicketService.findByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empName, empCode, division, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empNameAndEmpEntity_empCodeAndEmpEntity_division(empName, empCode, division);
		}
		if(num == 3) {
			//查询条件 empName
			entitys = myEmpTicketService.findByEmpEntity_empNameOrderByWeightDescModifytimeDesc(empName, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empName(empName);
		}
		if(num == 4) {
			//查询条件 empCode
			entitys = myEmpTicketService.findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(empCode, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empCode(empCode);
		}	
		if(num == 5) {
			// 查询条件 division
			entitys = myEmpTicketService.findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(division, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_division( division);
		}
		if(num == 6) {
			//查询条件 empName、empCode
			entitys = myEmpTicketService.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(empName, empCode, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empNameAndEmpEntity_empCode(empName, empCode);
		}
		if(num == 7) {
			//查询条件 empName、division
			entitys = myEmpTicketService.findByEmpEntity_empNameAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empName, division, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empNameAndEmpEntity_division(empName, division);
		}
		if(num == 8) {
			//查询条件 empCode、division
			entitys = myEmpTicketService.findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(empCode, division, pageNumber, pageSize);
			sum = myEmpTicketService.countByEmpEntity_empCodeAndEmpEntity_division(empCode, division);
		}
		List<EmpVoteDTO> DTOs = new ArrayList<EmpVoteDTO>();
		for (EmpTicketEntity entity : entitys) {
			EmpVoteDTO dto = LcjUtil.convertEmpVoteDTOByEntity(entity);
			DTOs.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", DTOs, pagination);
	}
	
	@RequestMapping(value = "/addWeight", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> addWeight(@RequestBody EmpVoteDTO dto) {
		EmpTicketEntity entity = LcjUtil.convertEmpVoteEntityByDTO(dto);
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
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(username, clientId, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(username, clientId, empName, empCode);
			}
			if(num == 3) {
				//查询条件：username\clientId\empName\
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(username, clientId, empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empName(username, clientId, empName);
			}
			if(num == 4) {
				//查询条件：username\clientId\\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(username, clientId, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientIdAndEmpEntity_empCode(username, clientId, empCode);
			}
			if(num == 5) {
				//查询条件：username\\empName\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime(username, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndEmpEntity_empNameAndEmpEntity_empCode(username, empName, empCode);
			}
			if(num == 6) {
				//查询条件：\clientId\empName\empCode
				entitys = myUserVoteForService.findByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( clientId, empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndEmpEntity_empNameAndEmpEntity_empCode(clientId, empName, empCode);
			}
			if(num == 7) {
				////查询条件：username\clientId\\
				entitys = myUserVoteForService.findByUserEntity_usernameAndUserEntity_clientIdOrderByRecordTime(username, clientId, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndUserEntity_clientId(username, clientId);
			}
			if(num == 8) {
				//查询条件：username\\empName\
				entitys = myUserVoteForService.findByUserEntity_usernameAndEmpEntity_empNameOrderByRecordTime(username, empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndEmpEntity_empName(username, empName);
			}
			if(num == 9) {
				//username\\\empCode
				entitys = myUserVoteForService.findByUserEntity_usernameAndEmpEntity_empCodeOrderByRecordTime(username, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_usernameAndEmpEntity_empCode(username, empCode);
			}
			if(num == 10) {
				//查询条件：\clientId\empName\
				entitys = myUserVoteForService.findByUserEntity_clientIdAndEmpEntity_empNameOrderByRecordTime(clientId, empName,pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndEmpEntity_empName( clientId, empName );
			}
			if(num == 11) {
				//查询条件：\clientId\\empCode
				entitys = myUserVoteForService.findByUserEntity_clientIdAndEmpEntity_empCodeOrderByRecordTime(clientId,  empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByUserEntity_clientIdAndEmpEntity_empCode(clientId,  empCode);
			}
			if(num == 12) {
				//查询条件：\\empName\empCode
				entitys = myUserVoteForService.findByEmpEntity_empNameAndEmpEntity_empCodeOrderByRecordTime( empName, empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByEmpEntity_empNameAndEmpEntity_empCode(empName, empCode);
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
				entitys = myUserVoteForService.findByEmpEntity_empNameOrderByRecordTime(empName, pageNumber, pageSize);
				sum = myUserVoteForService.countByEmpEntity_empName(empName);
			}
			if(num == 16) {
				//查询条件：empCode
				entitys = myUserVoteForService.findByEmpEntity_empCodeOrderByRecordTime(empCode, pageNumber, pageSize);
				sum = myUserVoteForService.countByEmpEntity_empCode( empCode);
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
