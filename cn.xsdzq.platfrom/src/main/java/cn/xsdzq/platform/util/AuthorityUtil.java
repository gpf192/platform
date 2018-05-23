package cn.xsdzq.platform.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xsdzq.platform.entity.AuthorityEntity;

public class AuthorityUtil {

	public static JSONArray getJsonAuthority(List<AuthorityEntity> authorityEntities) {

		JSONArray levelOneJsonArray = new JSONArray();
		List<AuthorityEntity> twoLevelList = new ArrayList<AuthorityEntity>();

		// 遍历第一次，得到一级菜单
		for (AuthorityEntity one : authorityEntities) {
			if (one.getLevel() == 1) {
				JSONObject levelOneJson = authorityEntityParseJSONObject(one);
				levelOneJsonArray.add(levelOneJson);
				JSONArray oneJsonArray = new JSONArray();
				levelOneJson.put("child", oneJsonArray);
			}
		}

		for (AuthorityEntity second : authorityEntities) {
			if (second.getLevel() == 2) {
				twoLevelList.add(second);
			}
		}

		for (AuthorityEntity twoAuthorityEntity : twoLevelList) {
			JSONObject levelTwoJson = authorityEntityParseJSONObject(twoAuthorityEntity);
			JSONArray twoJsonArray = new JSONArray();
			levelTwoJson.put("child", twoJsonArray);
			// 遍历第二次，得到二级菜单
			for (AuthorityEntity two : authorityEntities) {
				if (two.getLevel() == 3) {
					if (two.getParent_id() == twoAuthorityEntity.getId()) {
						twoJsonArray.add(authorityEntityParseJSONObject(two));
					}
				}
			}

			for (Object jObject : levelOneJsonArray) {
				JSONObject onejObject = (JSONObject) jObject;
				System.out.println("id: " + onejObject.getInteger("id"));
				if (levelTwoJson.getInteger("parent_id") == onejObject.getInteger("id")) {
					onejObject.getJSONArray("child").add(levelTwoJson);
				}
			}
		}
		return levelOneJsonArray;

	}

	public static JSONObject authorityEntityParseJSONObject(AuthorityEntity authorityEntity) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", authorityEntity.getId());
		jsonObject.put("authority", authorityEntity.getAuthority());
		jsonObject.put("menu_name", authorityEntity.getMenu_name());
		jsonObject.put("url", authorityEntity.getUrl());
		jsonObject.put("parent_id", authorityEntity.getParent_id());
		jsonObject.put("resource_path", authorityEntity.getResource_path());
		jsonObject.put("level", authorityEntity.getLevel());
		jsonObject.put("sort", authorityEntity.getSort());
		jsonObject.put("check", authorityEntity.isCheck());
		return jsonObject;
	}

}
