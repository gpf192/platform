package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.LcjPrizeRepository;
import cn.xsdzq.platform.dao.lcj.PrizeRepository;
import cn.xsdzq.platform.entity.lcj.LcjPrizeEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.service.InfoServiceImpl;

@Service(value = "lcjPrizeServiceImpl")
@Transactional(readOnly = true)
public class LcjPrizeServiceImpl implements LcjPrizeService{
	Logger logger = LogManager.getLogger(LcjPrizeServiceImpl.class.getName());
	@Autowired
	private LcjPrizeRepository prizeRepository;

	@Override
	public List<LcjPrizeEntity> getAllPrize() {
		// TODO Auto-generated method stub
		List<LcjPrizeEntity> infos = prizeRepository.getAllPrize();
		return infos;
	}

	@Override
	@Transactional
	public void addPrize(LcjPrizeEntity entity) {
		// TODO Auto-generated method stub
		prizeRepository.addPrize(entity);
	}

	@Override
	@Transactional
	public void deletePrize(LcjPrizeEntity entity) {
		// TODO Auto-generated method stub
		prizeRepository.deletePrize(entity);
	}

	@Override
	@Transactional
	public void modifyPrize(LcjPrizeEntity entity) {
		// TODO Auto-generated method stub
		LcjPrizeEntity info = prizeRepository.getPrizeById(entity.getId());
		info.setName(entity.getName());
		info.setAmount(entity.getAmount());
		info.setPrice(entity.getPrice());
		prizeRepository.modifyPrize(info);
	}

}

