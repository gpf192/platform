package cn.xsdzq.platform.controller.mall;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.xsdzq.platform.entity.mall.CardImportTempEntity;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.entity.mall.PresentEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListImportEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.CardImportTempDTO;
import cn.xsdzq.platform.model.mall.UserBlacklistDTO;
import cn.xsdzq.platform.model.mall.UserBlacklistImortDTO;
import cn.xsdzq.platform.service.mall.PageUserBlacklistService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.ImportExcelUtil;
import cn.xsdzq.platform.util.MethodUtil;
import cn.xsdzq.platform.util.UserManageUtil;
import cn.xsdzq.platform.util.mall.BlacklistUtil;
import cn.xsdzq.platform.util.mall.PresentUtil;

@RestController
@RequestMapping(value = "/mall/userr")
public class UserBlacklistController {
	@Autowired
	private PageUserBlacklistService userBlacklistService;
	
	
	@GetMapping(value = "/getAll")
	public Map<String, Object> getAll(HttpServletRequest request,@RequestParam String clientId,@RequestParam String clientName, @RequestParam int pageNumber,@RequestParam int pageSize) {
		int sum = 0 ;
		int num = MethodUtil.getMethodNum(clientId, clientName);
		List<UserBlackListEntity> entities = null;
		
		if(num == 1) {
			//全量查找
			try {
				entities = userBlacklistService.getAllUserBlacklist(pageNumber, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum = userBlacklistService.countAll();
		}
		if(num == 2) {
			//ab查找
			entities =userBlacklistService.findByClientIdLikeAndClientNameLikeOrderByCreateDateDesc(clientId, clientName, pageNumber, pageSize);
			sum = userBlacklistService.coutByClientIdLikeAndClientNameLike(clientId, clientName);
		}
		if(num == 3) {
			//a查找
			entities = userBlacklistService.findByClientIdLikeOrderByCreateDateDesc(clientId, pageNumber, pageSize);
			sum = userBlacklistService.coutByClientIdLike(clientId);
		}
		if(num == 4) {
			//b查找
			entities =userBlacklistService.findByClientNameLikeOrderByCreateDateDesc(clientName, pageNumber, pageSize);
			sum =userBlacklistService.coutByClientNameLike(clientName);
		}
		
		
		List<UserBlacklistDTO> dtos = new ArrayList<UserBlacklistDTO>();
		for (UserBlackListEntity entity : entities) {
			UserBlacklistDTO dto = BlacklistUtil.convertDTOByEntity(entity);
			dtos.add(dto);
		}
		Pagination pagination = new Pagination(pageNumber, pageSize, sum);
		return GsonUtil.buildMap(0, "ok", dtos,pagination);
	}
	
	@PostMapping(value = "/addUserBlacklist")
	public Map<String, Object> addUserBlacklist(@RequestBody UserBlacklistDTO dto) {
	
		UserBlackListEntity entity = BlacklistUtil.convertEntityByDTO(dto);
		User user = UserManageUtil.getUser();
		String name = user.getUsername();
		if(dto.getIsNew()==0) {
			entity.setCreatedBy(name);
		}
		userBlacklistService.add(entity);
		
		return GsonUtil.buildMap(0, "success", null);
	}
	@RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,  @RequestBody List<Long> ids) {		

		for(Long id:ids) {
			
			userBlacklistService.delete(id);
		}
		
		return GsonUtil.buildMap(0, "success", null);
	}
	//导入功能
	
	//导入excel
		 @RequestMapping(value="/uploadExcel",method={RequestMethod.POST}) 
		 public Map<String, Object>  uploadExcel(HttpServletRequest request) throws Exception { 
			 System.out.println("进入接口===================================(**********************************");
			 //
			 MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			 MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request); 
			 InputStream in =null; 
			  List<List<Object>> listob = null; 
			 MultipartFile file = multipartRequest.getFile("upfile"); 
			
			  if(file.isEmpty()){ 
			   throw new Exception("文件不存在！"); 
			  } 
			  in = file.getInputStream(); 
			  listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename()); 

			  in.close(); 
			//System.out.println("end------");
			//System.out.println("listob.size(): "+listob.size());//少后10条数据？
			  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出 
			  for (int i = 1; i < listob.size(); i++) { //i=0 则包括第一行标题数据
			   List<Object> lo = listob.get(i); //每行数据
			   //循环每行数据并插入
			   UserBlackListImportEntity entity = BlacklistUtil.toUserBlacklistTempEntity(lo);
			  // System.out.println("temp : "+entity.toString());
			   userBlacklistService.addImport(entity);
			  } 
			
			  return GsonUtil.buildMap(0, "success", null);
			} 
		//清空临时数据
		 @RequestMapping(value = "/deleteUserBlacklistImport", method = POST, produces = "application/json; charset=utf-8")
			@ResponseBody
			public Map<String, Object> deleteUserBlacklistImport() {
				int temps = userBlacklistService.countAllTemp();
				  if(temps==0) {
						return GsonUtil.buildMap(1, "当前无数据", null);	 
				  }
				  userBlacklistService.deleteAllImport();
				return GsonUtil.buildMap(0, "success", null);
			}
		 
		 
		//查询临时导入数据
		@GetMapping(value = "/getAllUserBlacklistTemp")
		public Map<String, Object> getAllUserBlacklistTemp(HttpServletRequest request, @RequestParam int pageNumber,@RequestParam int pageSize) {
			int sum = 0 ;
			List<UserBlackListImportEntity> entities = userBlacklistService.getAllUserBlacklistTemp(pageNumber, pageSize);
			List<UserBlacklistImortDTO> dtos = new ArrayList<UserBlacklistImortDTO>();
			for (UserBlackListImportEntity entity : entities) {
				UserBlacklistImortDTO dto = BlacklistUtil.convertUserBlacklistImortDTOByEntity(entity);
				dtos.add(dto);
			}
			sum = userBlacklistService.countAllTemp();
			Pagination pagination = new Pagination(pageNumber, pageSize, sum);
			return GsonUtil.buildMap(0, "ok", dtos,pagination);
		}
		//正式提交
		@RequestMapping(value = "/submit", method = POST, produces = "application/json; charset=utf-8")	
		@ResponseBody
		public Map<String, Object> submit() {
			//查询临时表数据
			List<UserBlackListImportEntity> temps =  new ArrayList<UserBlackListImportEntity>();
			//temps = pageCardImportTempService.findAllTemp();
			 temps = userBlacklistService.getAllUserBlacklistImport();
			//System.out.println("size :"+temps.size());
			//判断数据格式
			  //判断字段格式
			  if(temps.size()==0) {
					return GsonUtil.buildMap(1, "当前无数据，请先导入", null);
	 
			  }
			//检查当前临时表中cardId是否有重复
			  if(temps.size()>1) {
				  if(BlacklistUtil.isRepeat(temps)) {
					  return GsonUtil.buildMap(1, "附件中"+"clientId:"+"有重复，无法导入", null);
				  } 
			  }
			  
			for (UserBlackListImportEntity entity : temps) {
				//PresentEntity present =  presentService.getPresentEntitiesByCode(entity.getPresentCode());
				
				//检查是否存在
				List<UserBlackListEntity> li = userBlacklistService.getUserBlacklistByClientId(entity.getClientId());
				if(li.size() != 0) {		
					return GsonUtil.buildMap(1, "数据库中clientId:"+entity.getClientId()+"已存在，无法导入", null);
				}
				
				
			}
				
			//循环每条数据并插入正式表，同时父类加上相应数据,判断导入数据的时效性		
			for(UserBlackListImportEntity temp:temps) {
				 //临时类转换为 正式类，插入card表 end	
				UserBlackListEntity userBlackListEntity = BlacklistUtil.TempEntityToCardEntity(temp);
				//PresentEntity present =  presentService.getPresentEntitiesByCode(temp.getPresentCode());
				//present.setStoreUnused(present.getStoreUnused()+1);
				//userBlacklistService(present);//父类+1
				//presentCard.setPresent(present);//关联父类
				//presentCard.setCreateDate(new Date());
				//presentCard.setCreatedBy(username);
				//presentCard.setIsImport(1);
				userBlacklistService.add(userBlackListEntity);
			}
			System.out.println("总记录插入完成----");
			//最后清空临时表数据
			userBlacklistService.deleteAllImport();
			System.out.println("清空临时表入完成----");
			return GsonUtil.buildMap(0, "success", null);
		}
		
}
