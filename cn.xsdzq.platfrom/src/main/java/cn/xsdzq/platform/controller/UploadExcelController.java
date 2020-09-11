package cn.xsdzq.platform.controller;


import java.io.InputStream; 
import java.io.PrintWriter; 
import java.util.List; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.multipart.MultipartFile; 
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.xsdzq.platform.model.mall.InfoVo;
import cn.xsdzq.platform.util.ImportExcelUtil; 
 

@Controller 
@RequestMapping("/uploadExcel") 
public class UploadExcelController { 

 /** 
  * 描述：通过传统方式form表单提交方式导入excel文件 
  * @param request 
  * @throws Exception 
  */ 
	 @RequestMapping(value="/upload",method={RequestMethod.GET,RequestMethod.POST}) 
	 public String uploadExcel(HttpServletRequest request) throws Exception { 
		 System.out.println("进入接口===================================(**********************************");
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		  InputStream in =null; 
		  List<List<Object>> listob = null; 
		  MultipartFile file = multipartRequest.getFile("upfile"); 
			System.out.println("111------"+file.getOriginalFilename()+" size: "+file.getSize()+" tostring: "+file.toString());
			
		  if(file.isEmpty()){ 

		   throw new Exception("文件不存在！"); 

		  } 

		  in = file.getInputStream(); 
		  System.out.println("2222------");
		  listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename()); 
			System.out.println("5555------");

		  in.close(); 
		System.out.println("end------");
		System.out.println("listob.size(): "+listob.size());//少后10条数据？
		  //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出 
		  for (int i = 1; i < listob.size(); i++) { 
		   List<Object> lo = listob.get(i); 
		   InfoVo vo = new InfoVo(); 
		   vo.setCode(String.valueOf(lo.get(0))); 
		   vo.setName(String.valueOf(lo.get(1))); 
		   vo.setDate(String.valueOf(lo.get(2))); 
		   vo.setMoney(String.valueOf(lo.get(3))); 
		
		   System.out.println("打印信息-->机构:"+vo.getCode()+" 名称："+vo.getName()+" 时间："+vo.getDate()+" 资产："+vo.getMoney()); 
		  } 
		  return "result"; 
		 } 
}