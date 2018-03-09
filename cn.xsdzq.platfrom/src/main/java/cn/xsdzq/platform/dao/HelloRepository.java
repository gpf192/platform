package cn.xsdzq.platform.dao;

import cn.xsdzq.platform.entity.HelloEntity;

public interface HelloRepository {

	public void addHello(HelloEntity helloEntity);

	public HelloEntity findOne();

}
