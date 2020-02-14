package cn.xsdzq.platform.service.lcj;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.AwardResultRepository;
import cn.xsdzq.platform.entity.lcj.AwardResultEntity;

@Service(value = "awardResultServiceImpl")
@Transactional(readOnly = true)
public class AwardResultServiceImpl implements AwardResultService{

	@Autowired
	private AwardResultRepository awardResultRepository;
	

	@Override
	public List<AwardResultEntity> getAllAwardResult(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByOrderByRecordTimeDesc(pageRequest);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)awardResultRepository.count();
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, Date endDate, String AwardEntity_imageName, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, endDate, AwardEntity_imageName, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate, Date endDate, String AwardEntity_imageName, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(beginDate, endDate, AwardEntity_imageName, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualOrderByRecordTime(Date beginDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualOrderByRecordTimeDesc(beginDate, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqual(Date beginDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqual(beginDate);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeLessThanEqualOrderByRecordTime(Date endDate, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualOrderByRecordTimeDesc(endDate, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqual(Date endDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqual(endDate);
	}
	@Override
	public List<AwardResultEntity> findByAwardEntity_imageNameOrderByRecordTime(String AwardEntity_imageName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByAwardEntity_imageNameOrderByRecordTimeDesc(AwardEntity_imageName, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByAwardEntity_imageName(String AwardEntity_imageName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByAwardEntity_imageName(AwardEntity_imageName);
	}
	@Override
	public List<AwardResultEntity> findByUserEntity_clientIdOrderByRecordTime(String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByUserEntity_clientIdOrderByRecordTimeDesc(UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByUserEntity_clientId(String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByUserEntity_clientId(UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTime(
			Date beginDate, Date endDate, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualOrderByRecordTimeDesc(beginDate, endDate, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqual(beginDate, endDate);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameOrderByRecordTime(Date beginDate,
			String AwardEntity_imageName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc(beginDate, AwardEntity_imageName, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndAwardEntity_imageName(Date beginDate, String AwardEntity_imageName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndAwardEntity_imageName(beginDate, AwardEntity_imageName);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTime(Date beginDate,
			String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndUserEntity_clientId(Date beginDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndUserEntity_clientId(beginDate, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTime(Date endDate,
			String AwardEntity_imageName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc(endDate, AwardEntity_imageName, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndAwardEntity_imageName(Date endDate, String AwardEntity_imageName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndAwardEntity_imageName(endDate, AwardEntity_imageName);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(Date endDate,
			String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(endDate, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndUserEntity_clientId(Date endDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndUserEntity_clientId(endDate, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(String AwardEntity_imageName, String UserEntity_clientId,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(AwardEntity_imageName, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByAwardEntity_imageNameAndUserEntity_clientId(String AwardEntity_imageName, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByAwardEntity_imageNameAndUserEntity_clientId(AwardEntity_imageName, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTime(
			Date beginDate, Date endDate, String AwardEntity_imageName, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageNameOrderByRecordTimeDesc(beginDate,endDate, AwardEntity_imageName, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageName(Date beginDate, Date endDate,
			String AwardEntity_imageName) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndAwardEntity_imageName(beginDate,endDate, AwardEntity_imageName);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, String AwardEntity_imageName, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate, AwardEntity_imageName, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date beginDate, String AwardEntity_imageName,
			String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(beginDate, AwardEntity_imageName, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTime(Date endDate,
			String AwardEntity_imageName, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientIdOrderByRecordTimeDesc(endDate, AwardEntity_imageName, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(Date endDate, String AwardEntity_imageName, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeLessThanEqualAndAwardEntity_imageNameAndUserEntity_clientId(endDate, AwardEntity_imageName, UserEntity_clientId);
	}
	@Override
	public List<AwardResultEntity> findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTime(
			Date beginDate, Date endDate, String UserEntity_clientId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<AwardResultEntity> pages = awardResultRepository.findByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientIdOrderByRecordTimeDesc(beginDate,endDate, UserEntity_clientId, pageable);
		List<AwardResultEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(Date beginDate,
			Date endDate, String UserEntity_clientId) {
		// TODO Auto-generated method stub
		return awardResultRepository.countByRecordTimeGreaterThanEqualAndRecordTimeLessThanEqualAndUserEntity_clientId(beginDate, endDate, UserEntity_clientId);
	}
	

}
