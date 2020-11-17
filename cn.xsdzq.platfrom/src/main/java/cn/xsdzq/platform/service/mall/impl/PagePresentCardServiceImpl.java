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
	public List<PresentCardEntity> findByCardIdLikeOrderByCreateDateDesc(String cardId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentCardEntity> pages = pagePresentCardRepository.findByCardIdLikeOrderByCreateDateDesc(cardId, pageRequest);
			
		List<PresentCardEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCardIdLike(String cardId) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByCardIdLike(cardId);
	}

	@Override
	public List<PresentCardEntity> findByPresentIdOrderByCreateDateDesc(long presentId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentCardEntity> pages = pagePresentCardRepository.findByPresentIdOrderByCreateDateDesc(presentId, pageRequest);
			
		List<PresentCardEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByPresentId(long presentId) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByPresentId(presentId);
	}

	@Override
	public List<PresentCardEntity> findByCardIdLikeAndPresentIdOrderByCreateDateDesc(String cardId, long presentId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PresentCardEntity> pages = pagePresentCardRepository.findByCardIdLikeAndPresentIdOrderByCreateDateDesc(cardId, presentId, pageRequest);
			
		List<PresentCardEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCardIdLikeAndPresentId(String cardId, long presentId) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByCardIdLikeAndPresentId(cardId, presentId);
	}

	@Override
	public int countByPresentIdAndConvertStatusAndCardStatus(long presentId,int convertStatus, int cardStatus) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByPresentIdAndConvertStatusAndCardStatus( presentId,convertStatus, cardStatus);
	}

	@Override
	public int countByPresentIdAndCardStatus(long presentId,int cardStatus) {
		// TODO Auto-generated method stub
		return pagePresentCardRepository.countByPresentIdAndCardStatus(presentId,cardStatus);
	}
}
