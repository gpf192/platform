package cn.xsdzq.platform.service;

import java.util.ArrayList;
import java.util.Comparator;
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
import cn.xsdzq.platform.util.AuthorityJSONComparator;

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
				// 过滤因为角色叠加造成多余的权限
				if (!authorityEntitiesList.contains(authorityEntity)) {
					authorityEntitiesList.add(authorityEntity);
				}
			}
		}
		// 第三，根据权限遍历出菜单
		JSONObject result = calculateMenu(authorityEntitiesList);
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
		// 1级菜单遍历的结果排序
		oneLevelList.sort(Comparator.naturalOrder());
		for (AuthorityEntity twoAuthorityEntity : oneLevelList) {
			JSONObject levelOneJson = authorityEntityParseJSONObject(twoAuthorityEntity);
			List<JSONObject> twoJsonArray = new ArrayList<JSONObject>();
			levelOneJson.put("child", twoJsonArray);
			// 遍历第二次，得到二级菜单
			for (AuthorityEntity two : authorityEntitiesList) {
				if (two.getLevel() == 2) {
					if (two.getParent_id() == twoAuthorityEntity.getId()) {
						twoJsonArray.add(authorityEntityParseJSONObject(two));
					}
				}
			}
			// 2级菜单遍历的结果排序
			twoJsonArray.sort(new AuthorityJSONComparator());
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
		jsonObject.put("forward", authorityEntity.getForward());
		return jsonObject;
	}

}
