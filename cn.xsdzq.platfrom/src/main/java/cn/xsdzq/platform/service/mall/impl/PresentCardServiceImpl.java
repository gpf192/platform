package cn.xsdzq.platform.service.mall.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PresentCardRepository;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.model.mall.PresentCardDTO;
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
		//PresentCardEntity p = presentCardRepository.findById(presentCard.getId()).get();
		//presentCard.setCreateDate(p.getCreateDate());
		//presentCard.setModifytime(new Date());
		presentCardRepository.save(presentCard);

	}

	@Override
	public List<PresentCardEntity> getPresentCardEntities() {
		// TODO Auto-generated method stub
		return presentCardRepository.findAll();
	}
	
	@Override
	public PresentCardEntity findByCardId(String cardId) {
		return presentCardRepository.findByCardId(cardId);
	}

	@Override
	@Transactional
	public void deletePresentCard(PresentCardDTO dto) {
		// TODO Auto-generated method stub
		PresentCardEntity entity = presentCardRepository.findById(dto.getId()).get();
		presentCardRepository.delete(entity);
	}

	@Override
	public PresentCardEntity findById(long id) {
		// TODO Auto-generated method stub
		return presentCardRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void deletePresentCardById(long id) {
		// TODO Auto-generated method stub
		PresentCardEntity entity = presentCardRepository.findById(id).get();
		presentCardRepository.delete(entity);
	}


}
