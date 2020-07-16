package cn.xsdzq.platform.service.lcj;

import java.util.List;

import cn.xsdzq.platform.entity.lcj.ParamEntity;

public interface MyParamService {
	int countAll();
	List<ParamEntity> getAllParam(int pageNumber, int pageSize);
	
	List<ParamEntity>findByCodeLikeOrderById(String code, int pageNumber, int pageSize);
    int countByCodeLike(String code);
}
