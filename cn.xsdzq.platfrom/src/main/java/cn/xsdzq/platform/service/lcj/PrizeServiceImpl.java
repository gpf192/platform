package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyPrizeRepository;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;

@Service(value = "prizeServiceImpl")
@Transactional(readOnly = true)
public class PrizeServiceImpl implements PrizeService{
	@Autowired
	private MyPrizeRepository myPrizeRepository;
	@Override
	public List<PrizeEntity> getAllPrize(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<PrizeEntity> pages = myPrizeRepository.findByOrderById(pageRequest);
		List<PrizeEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myPrizeRepository.count();
	}
}
