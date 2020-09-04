package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.xsdzq.platform.dao.mall.PresentResultRepository;
import cn.xsdzq.platform.entity.mall.PresentResultEntity;
import cn.xsdzq.platform.service.mall.PresentResultService;



public class PresentResultServiceImpl implements PresentResultService {

	@Autowired
	private PresentResultRepository presentResultRepository;

	@Override
	public List<PresentResultEntity> getResultEntities() {
		// TODO Auto-generated method stub
		return presentResultRepository.findAll();
	}

}
