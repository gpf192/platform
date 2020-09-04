package cn.xsdzq.platform.service.mall.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.service.mall.MallUserService;



@Service(value = "mallUserServiceImpl")
@Transactional(readOnly = true)
public class MallUserServiceImpl implements MallUserService {

	@Override
	public void addMallUser(MallUserEntity mallUserEntity) {
		// TODO Auto-generated method stub

	}

}
