package cn.xsdzq.platform.service.lcj;

import cn.xsdzq.platform.entity.lcj.ParamEntity;

public interface ParamService {
	ParamEntity getValueByCode(String  code);
	void deleteInfo(ParamEntity entity);

	void addInfo(ParamEntity entity);

	void modifyInfo(ParamEntity entity);
}
