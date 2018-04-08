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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.xsdzq.platform.entity.RecordEntity;
import cn.xsdzq.platform.service.IRecordService;

public class LoggerFilter implements Filter {

	@Autowired
	IRecordService recordService;

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
		String url = httpRequest.getRequestURL().toString();
		String path = httpRequest.getRequestURI();
		String channel = httpRequest.getParameter("channel");
		String ip = httpRequest.getRemoteAddr();
		String agent = httpRequest.getHeader("User-Agent");

		RecordEntity recordEntity = new RecordEntity();

		recordEntity.setChannel(channel);
		recordEntity.setUri(url);
		recordEntity.setIp(ip);
		recordEntity.setPath(path);
		recordEntity.setAgent(agent);

		System.out.println("agent: " + agent);
		recordService.mergeRecord(recordEntity);

		/*
		 * HttpSession session = httpRequest.getSession(); String ip =
		 * httpRequest.getRemoteAddr(); String page = httpRequest.getRequestURI();
		 * String contextPath = httpRequest.getContextPath(); String servletPath =
		 * httpRequest.getServletPath(); String addr = httpRequest.getLocalAddr();
		 * String queryStirng = httpRequest.getQueryString(); String pathInfo =
		 * httpRequest.getPathInfo(); String aa = httpRequest.getParameter("aa"); String
		 * url = httpRequest.getRequestURL().toString(); System.out.println("url:" + url
		 * + ";" + "addr: " + addr + ";" + "queryStirng" + queryStirng + ";" +
		 * "pathInfo" + pathInfo + ";" + "aa" + aa);
		 * 
		 * System.out.println("doFilter sessionId=" + session.getId() + ",ip=" + ip +
		 * ",page=" + page + ",contextPath=" + contextPath + ",servletPath=" +
		 * servletPath);
		 * 
		 * Map<String, String[]> params = request.getParameterMap(); String queryString2
		 * = ""; for (String key : params.keySet()) { String[] values = params.get(key);
		 * for (int i = 0; i < values.length; i++) { String value = values[i];
		 * queryString2 += key + "=" + value + "&"; } } // 去掉最后一个空格 if
		 * (queryString2.length() > 0) { queryString2 = queryString2.substring(0,
		 * queryString2.length() - 1); }
		 * 
		 * System.out.println("queryString2:" + queryString2);
		 * 
		 */
		chain.doFilter(httpRequest, httpResponse);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
