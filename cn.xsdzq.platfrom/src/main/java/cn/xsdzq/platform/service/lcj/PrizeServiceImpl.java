package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.PrizeRepository;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.service.InfoServiceImpl;

@Service(value = "prizeServiceImpl")
@Transactional(readOnly = true)
public class PrizeServiceImpl implements PrizeService{
	Logger logger = LogManager.getLogger(InfoServiceImpl.class.getName());
	@Autowired
	private PrizeRepository prizeRepository;

	@Override
	public List<PrizeEntity> getAllPrize() {
		// TODO Auto-generated method stub
		List<PrizeEntity> infos = prizeRepository.getAllPrize();
		return infos;
	}

	@Override
	@Transactional
	public void addPrize(PrizeEntity entity) {
		// TODO Auto-generated method stub
		prizeRepository.addPrize(entity);
	}

	@Override
	@Transactional
	public void deletePrize(PrizeEntity entity) {
		// TODO Auto-generated method stub
		prizeRepository.deletePrize(entity);
	}

	@Override
	@Transactional
	public void modifyPrize(PrizeEntity entity) {
		// TODO Auto-generated method stub
		prizeRepository.modifyPrize(entity);
	}

}
