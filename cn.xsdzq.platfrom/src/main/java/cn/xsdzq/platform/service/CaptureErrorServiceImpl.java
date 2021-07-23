package cn.xsdzq.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.CaptureErrorRepository;
import cn.xsdzq.platform.entity.CaptureErrorEntity;


@Service
@Transactional
public class CaptureErrorServiceImpl implements CaptureErrorService {

	@Autowired
	private CaptureErrorRepository captureErrorRepository;

	@Override
	@Transactional
	public void addErrorInfo(CaptureErrorEntity captureErrorEntity) {
		// TODO Auto-generated method stub

		captureErrorRepository.save(captureErrorEntity);

	}

}
