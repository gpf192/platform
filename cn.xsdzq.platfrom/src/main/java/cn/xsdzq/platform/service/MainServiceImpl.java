package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;

@Service(value = "mainServiceImpl")
@Transactional(readOnly = true)
public class MainServiceImpl implements IMainService {

	@Override
	public JSONObject getMenu(UserEntity userEntity) {
		// TODO Auto-generated method stub

		// 首先，得到用户对应的角色
		Set<RoleEntity> roleEntities = userEntity.getRoleEntities();
		// 第二，得到所有权限
		Iterator<RoleEntity> roleIterable = roleEntities.iterator();
		List<AuthorityEntity> authorityEntitiesList = new ArrayList<AuthorityEntity>();
		while (roleIterable.hasNext()) {
			RoleEntity rEntity = roleIterable.next();
			Set<AuthorityEntity> authorityEntities = rEntity.getAuthorityEntities();
			Iterator<AuthorityEntity> authoritIterable = authorityEntities.iterator();
			while (authoritIterable.hasNext()) {
				AuthorityEntity authorityEntity = (AuthorityEntity) authoritIterable.next();
				authorityEntitiesList.add(authorityEntity);
			}
		}

		// 第三，根据权限遍历出菜单
		JSONObject result = calculateMenu(authorityEntitiesList);
		System.out.println(result);
		return result;
	}

	public JSONObject calculateMenu(List<AuthorityEntity> authorityEntitiesList) {

		JSONObject menu = new JSONObject();
		JSONArray levelOneJsonArray = new JSONArray();
		List<AuthorityEntity> oneLevelList = new ArrayList<AuthorityEntity>();
		// 遍历第一次，得到一级菜单
		for (AuthorityEntity first : authorityEntitiesList) {
			if (first.getLevel() == 1) {
				oneLevelList.add(first);
			}
		}

		for (AuthorityEntity twoAuthorityEntity : oneLevelList) {
			JSONObject levelOneJson = authorityEntityParseJSONObject(twoAuthorityEntity);
			JSONArray twoJsonArray = new JSONArray();
			levelOneJson.put("child", twoJsonArray);
			// 遍历第二次，得到二级菜单
			for (AuthorityEntity two : authorityEntitiesList) {
				if (two.getLevel() == 2) {
					if (two.getParent_id() == twoAuthorityEntity.getId()) {
						twoJsonArray.add(authorityEntityParseJSONObject(two));
					}
				}
			}
			levelOneJsonArray.add(levelOneJson);
		}
		// 遍历第三次，得到相应权限 保留三级权限的遍历
		/*
		 * for(AuthorityEntity twoAuthorityEntity : oneLevelList) {
		 * 
		 * 
		 * }
		 */

		menu.put("menu", levelOneJsonArray);
		return menu;
	}

	public JSONObject authorityEntityParseJSONObject(AuthorityEntity authorityEntity) {
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
