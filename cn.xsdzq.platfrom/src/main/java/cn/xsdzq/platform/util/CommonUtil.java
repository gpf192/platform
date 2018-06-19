package cn.xsdzq.platform.util;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {

	public static JSONObject getJsonByQueryString(String querystring) {
		JSONObject jsonObject = new JSONObject();
		if (querystring == null) {
			return jsonObject;
		}
		String[] one = querystring.split("&");
		for (int i = 0; i < one.length; i++) {
			String[] two = one[i].split("=");
			jsonObject.put(two[0], two[1]);
		}
		return jsonObject;
	}

}
