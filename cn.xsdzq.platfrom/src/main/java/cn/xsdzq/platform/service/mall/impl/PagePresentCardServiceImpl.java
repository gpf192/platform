package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.PagePresentCardRepository;
import cn.xsdzq.platform.entity.mall.PresentCardEntity;
import cn.xsdzq.platform.service.mall.PagePresentCardService;

@Service(value = "pagePresentCardServiceImpl")
@Transactional(readOnly = true)
public class PagePresentCardServiceImpl implements PagePresentCardService{
	@Autowired
	private PagePresentCardRepository pagePresentCardRepository;

	@Override
	public List<PresentCardEntity> findByOrderByCreateDateDesc(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentCardEntity> pages = pagePresentCardRepository.findByOrderByCreateDateDesc(pageRequest);
			
		List<PresentCardEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int) pagePresentCardRepository.count();
	}

	@Override
	public List<PresentCardEntity> findByCardIdOrderByCreateDateDesc(String cardId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentCardEntity> pages = pagePresentCardRepository.findByCardIdOrderByCreateDateDesc(cardId, pageRequest);
			
		List<PresentCardEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCardId(String cardId) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByCardId(cardId);
	}
}
