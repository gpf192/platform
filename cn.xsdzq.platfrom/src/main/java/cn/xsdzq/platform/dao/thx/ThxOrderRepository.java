package cn.xsdzq.platform.dao.thx;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

@Repository
public interface ThxOrderRepository {

	void addInfo(ThxOrderEntity entity);

	void modifyInfo(ThxOrderEntity entity);
}
