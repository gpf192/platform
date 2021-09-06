package cn.xsdzq.platform.service.mall.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.mall.UserBlacklistImportRepository;
import cn.xsdzq.platform.dao.mall.UserBlacklistRepository;
import cn.xsdzq.platform.entity.mall.UserBlackListEntity;
import cn.xsdzq.platform.entity.mall.UserBlackListImportEntity;
import cn.xsdzq.platform.service.mall.PageUserBlacklistService;

@Service
@Transactional(readOnly = true)
public class PageUserBlacklistServiceImpl implements PageUserBlacklistService {
	@Autowired
	private UserBlacklistRepository userBlacklistRepository;

	@Autowired
	private UserBlacklistImportRepository userBlacklistImportRepository;
	
	@Override
	public List<UserBlackListEntity> getAllUserBlacklist(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserBlackListEntity> pages = userBlacklistRepository.findByOrderByCreateDateDesc(pageRequest);
			
		List<UserBlackListEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)userBlacklistRepository.count();
	}
	
	@Override
	public List<UserBlackListEntity> findByClientIdLikeOrderByCreateDateDesc(String ClientId, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserBlackListEntity> pages = userBlacklistRepository.findByClientIdLikeOrderByCreateDateDesc(ClientId,pageRequest);
			
		List<UserBlackListEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int coutByClientIdLike(String ClientId) {
		// TODO Auto-generated method stub
		return userBlacklistRepository.countByClientIdLike(ClientId);
	}
	
	@Override
	public List<UserBlackListEntity> findByClientNameLikeOrderByCreateDateDesc(String clientName, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserBlackListEntity> pages = userBlacklistRepository.findByClientNameLikeOrderByCreateDateDesc(clientName,pageRequest);
			
		List<UserBlackListEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int coutByClientNameLike(String clientName) {
		// TODO Auto-generated method stub
		return userBlacklistRepository.countByClientNameLike(clientName);
	}
	
	@Override
	public List<UserBlackListEntity> findByClientIdLikeAndClientNameLikeOrderByCreateDateDesc(String clientId,
			String clientName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserBlackListEntity> pages = userBlacklistRepository.findByClientIdLikeAndClientNameLikeOrderByCreateDateDesc(clientId, clientName,pageRequest);
			
		List<UserBlackListEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int coutByClientIdLikeAndClientNameLike(String clientId, String clientName) {
		// TODO Auto-generated method stub
		return userBlacklistRepository.countByClientIdLikeAndClientNameLike(clientId, clientName);
	}



	
////
	
	@Override
	@Transactional
	public void add(UserBlackListEntity entity) {
		// TODO Auto-generated method stub
		UserBlackListEntity en = new UserBlackListEntity();
		en = userBlacklistRepository.findById(entity.getId());
		if(en == null) {
			userBlacklistRepository.save(entity);
		}else {
			en.setClientName(entity.getClientName());
			en.setDepartmentDesc(entity.getDepartmentDesc());
			
			userBlacklistRepository.save(en);
		}
		
	}

	@Override
	@Transactional
	public void delete(long id) {
		// TODO Auto-generated method stub
		userBlacklistRepository.deleteById(id);
	}
//
	@Override
	public List<UserBlackListImportEntity> getAllUserBlacklistTemp(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<UserBlackListImportEntity> pages = userBlacklistImportRepository.findBy(pageRequest);
			
		List<UserBlackListImportEntity> infos = pages.getContent();
		return infos;
	}

	@Override
	public int countAllTemp() {
		// TODO Auto-generated method stub
		return (int)userBlacklistImportRepository.count();
	}

	@Override
	@Transactional
	public void addImport(UserBlackListImportEntity userBlackListImportEntity) {
		// TODO Auto-generated method stub
		userBlacklistImportRepository.save(userBlackListImportEntity);
	}

	@Override
	@Transactional
	public void deleteAllImport() {
		// TODO Auto-generated method stub
		userBlacklistImportRepository.deleteAll();

	}

	@Override
	public List<UserBlackListImportEntity> getAllUserBlacklistImport() {
		// TODO Auto-generated method stub
		return userBlacklistImportRepository.findBy();
	}

	@Override
	public List<UserBlackListEntity> getUserBlacklistByClientId(String clientId) {
		// TODO Auto-generated method stub
		return userBlacklistRepository.findByClientId(clientId);
	}


	
}
