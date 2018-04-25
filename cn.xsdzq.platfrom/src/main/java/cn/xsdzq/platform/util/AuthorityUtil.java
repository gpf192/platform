package cn.xsdzq.platform.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xsdzq.platform.entity.AuthorityEntity;

public class AuthorityUtil {

	public static JSONArray getJsonAuthority(List<AuthorityEntity> authorityEntities) {

		JSONArray levelOneJsonArray = new JSONArray();
		List<AuthorityEntity> oneLevelList = new ArrayList<AuthorityEntity>();
		// 遍历第一次，得到一级菜单
		for (AuthorityEntity first : authorityEntities) {
			if (first.getLevel() == 2) {
				oneLevelList.add(first);
			}
		}

		for (AuthorityEntity twoAuthorityEntity : oneLevelList) {
			JSONObject levelOneJson = authorityEntityParseJSONObject(twoAuthorityEntity);
			JSONArray twoJsonArray = new JSONArray();
			levelOneJson.put("child", twoJsonArray);
			// 遍历第二次，得到二级菜单
			for (AuthorityEntity two : authorityEntities) {
				if (two.getLevel() == 3) {
					if (two.getParent_id() == twoAuthorityEntity.getId()) {
						twoJsonArray.add(authorityEntityParseJSONObject(two));
					}
				}
			}
			levelOneJsonArray.add(levelOneJson);
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
		return jsonObject;
	}

}
