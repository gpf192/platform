package cn.xsdzq.platform.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import cn.xsdzq.platform.util.GsonUtil;

public class BaseController {
	@ExceptionHandler
	@ResponseBody
	public Map<String, Object> handleException(Exception exception) {
		if (exception instanceof MySQLIntegrityConstraintViolationException) {
			return GsonUtil.buildMap(2, "Duplicate entry", null);
		}
		return GsonUtil.buildMap(1, "fail", null);
	}
}
