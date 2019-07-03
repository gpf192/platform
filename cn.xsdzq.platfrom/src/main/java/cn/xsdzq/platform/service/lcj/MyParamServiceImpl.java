package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyParamRepository;
import cn.xsdzq.platform.entity.lcj.ParamEntity;

@Service(value = "myParamServiceImpl")
@Transactional(readOnly = true)
public class MyParamServiceImpl implements MyParamService{
	@Autowired
	private MyParamRepository myParamRepository;
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myParamRepository.count();
	}

	@Override
	public List<ParamEntity> getAllParam(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ParamEntity> pages = myParamRepository.findByOrderById(pageRequest);
		List<ParamEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public List<ParamEntity> findByCodeLikeOrderById(String code, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<ParamEntity> pages = myParamRepository.findByCodeLikeOrderById(code, pageRequest);
		List<ParamEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countByCodeLike(String code) {
		// TODO Auto-generated method stub
		return myParamRepository.countByCodeLike(code);
	}

}
