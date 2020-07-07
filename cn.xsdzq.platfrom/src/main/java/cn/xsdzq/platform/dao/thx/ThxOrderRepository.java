package cn.xsdzq.platform.dao.thx;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

@Repository
public interface ThxOrderRepository {

	void addInfo(ThxOrderEntity entity);

	void modifyInfo(ThxOrderEntity entity);
	List<ThxOrderEntity> getEntityByOrderId(String orderId);
}
