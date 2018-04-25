package cn.xsdzq.platform.service;

import com.alibaba.fastjson.JSONObject;

import cn.xsdzq.platform.entity.UserEntity;

public interface IMainService {

	public JSONObject getMenu(UserEntity userEntity);

}
