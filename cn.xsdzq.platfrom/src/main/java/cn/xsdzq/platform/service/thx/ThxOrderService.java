package cn.xsdzq.platform.service.thx;

import cn.xsdzq.platform.entity.InfoEntity;
import cn.xsdzq.platform.entity.thx.ThxOrderEntity;

public interface ThxOrderService {
	void addInfo(ThxOrderEntity infoEntity);


	void modifyInfo(ThxOrderEntity infoEntity);
}
