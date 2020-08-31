package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.xsdzq.platform.dao.mall.PresentRecordRepository;
import cn.xsdzq.platform.entity.mall.PresentRecordEntity;



public class PresentRecordServiceImpl implements PresentRecordService {

	@Autowired
	private PresentRecordRepository presentRecordRepository;

	@Override
	public List<PresentRecordEntity> getRecordEntities() {
		// TODO Auto-generated method stub
		return presentRecordRepository.findAll();
	}

}
