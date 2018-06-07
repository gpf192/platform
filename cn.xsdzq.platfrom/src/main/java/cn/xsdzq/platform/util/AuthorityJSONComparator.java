package cn.xsdzq.platform.util;

import java.util.Comparator;

import com.alibaba.fastjson.JSONObject;

public class AuthorityJSONComparator implements Comparator<JSONObject> {

	@Override
	public int compare(JSONObject o1, JSONObject o2) {
		// TODO Auto-generated method stub
		int sort1 = o1.getInteger("sort");
		int sort2 = o2.getInteger("sort");
		if (sort1 > sort2) {
			return 1;
		} else {
			return -1;
		}
	}

}
