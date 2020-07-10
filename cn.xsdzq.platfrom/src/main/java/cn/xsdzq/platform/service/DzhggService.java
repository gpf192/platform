package cn.xsdzq.platform.service;

import java.util.List;

import cn.xsdzq.platform.entity.DzhggEntity;

public interface DzhggService {
	int countAll();
	List<DzhggEntity> getAll(int pageNumber, int pageSize);
	
	List<DzhggEntity> findByActivityAndNameAndPhoneOrderByRecordtimeDesc(String activity, String name, String phone, int pageNumber, int pageSize);
	int countByActivityAndNameAndPhone(String activity, String name, String phone);
	
	List<DzhggEntity> findByActivityAndNameOrderByRecordtimeDesc(String activity, String name, int pageNumber, int pageSize);
	int countByActivityAndName(String activity, String name );
	
	List<DzhggEntity> findByActivityAndPhoneOrderByRecordtimeDesc(String activity, String phone, int pageNumber, int pageSize);
	int countByActivityAndPhone(String activity, String phone);
	
	List<DzhggEntity> findByNameAndPhoneOrderByRecordtimeDesc( String name, String phone, int pageNumber, int pageSize);
	int countByNameAndPhone( String name, String phone);
	
	List<DzhggEntity> findByActivityOrderByRecordtimeDesc( String activity, int pageNumber, int pageSize);
	int countByActivity( String activity);
	
	List<DzhggEntity> findByNameOrderByRecordtimeDesc( String name, int pageNumber, int pageSize);
	int countByName( String name);
	
	List<DzhggEntity> findByPhoneOrderByRecordtimeDesc( String phone, int pageNumber, int pageSize);
	int countByPhone( String phone);
}
