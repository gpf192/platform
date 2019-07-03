package cn.xsdzq.platform.dao.lcj;

import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.lcj.ParamEntity;


@Repository
public interface ParamRepository {
	ParamEntity getValueByCode(String  code);
	void deleteInfo(ParamEntity entity);

	void addInfo(ParamEntity entity);

	void modifyInfo(ParamEntity entity);
}
