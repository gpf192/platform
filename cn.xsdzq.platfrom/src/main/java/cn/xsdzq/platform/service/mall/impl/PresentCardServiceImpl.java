package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PresentCardRepository;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.service.mall.PresentCardService;



@Service
@Transactional(readOnly = true)
public class PresentCardServiceImpl implements PresentCardService {

	@Autowired
	private PresentCardRepository presentCardRepository;

	@Override
	@Transactional
	public void addPresentCard(PresentCardEntity presentCard) {
		// TODO Auto-generated method stub
		
		presentCardRepository.save(presentCard);

	}

	@Override
	public List<PresentCardEntity> getPresentCardEntities() {
		// TODO Auto-generated method stub
		return presentCardRepository.findAll();
	}

}
