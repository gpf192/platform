package cn.xsdzq.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;

import cn.xsdzq.platform.entity.RecordEntity;
import cn.xsdzq.platform.service.IRecordService;
import cn.xsdzq.platform.util.CommonUtil;

public class LoggerFilter implements Filter {

	@Autowired
	IRecordService recordService;

	Logger logger = LogManager.getLogger(LoggerFilter.class.getName());

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		recordService = (IRecordService) ctx.getBean("recordServiceImpl");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 拦截html请求
		// 请求的完整地址
		JSONObject channelJson = CommonUtil.getJsonByQueryString(httpRequest.getQueryString());
		logger.info("channel: " + httpRequest.getQueryString());
		String url = httpRequest.getRequestURL().toString();
		String path = httpRequest.getRequestURI();
		String channel = channelJson.getString("channel");
		String ip = httpRequest.getRemoteAddr();
		String agent = httpRequest.getHeader("User-Agent");
		httpRequest.getQueryString();
		if (null == channel || channel.equals("")) {
			channel = "default";
		}
		logger.error("channel: " + channel);
		RecordEntity recordEntity = new RecordEntity();
		recordEntity.setChannel(channel);
		recordEntity.setUri(url);
		recordEntity.setIp(ip);
		recordEntity.setPath(path);
		recordEntity.setAgent(agent);
		recordService.mergeRecord(recordEntity);
		chain.doFilter(httpRequest, httpResponse);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
