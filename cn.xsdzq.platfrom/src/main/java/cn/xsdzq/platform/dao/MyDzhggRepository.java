package cn.xsdzq.platform.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.DzhggEntity;

public interface MyDzhggRepository extends PagingAndSortingRepository<DzhggEntity, Long> {
	Page<DzhggEntity> findByOrderByRecordtimeDesc(Pageable pageable);
	
	Page<DzhggEntity> findByActivityAndNameLikeAndPhoneLikeOrderByRecordtimeDesc(String activity, String name, String phone, Pageable pageable);
	int countByActivityAndNameLikeAndPhoneLike(String activity, String name, String phone);
	
	Page<DzhggEntity> findByActivityAndNameLikeOrderByRecordtimeDesc(String activity, String name , Pageable pageable);
	int countByActivityAndNameLike(String activity, String name);
	
	Page<DzhggEntity> findByActivityAndPhoneLikeOrderByRecordtimeDesc(String activity , String phone, Pageable pageable);
	int countByActivityAndPhoneLike(String activity , String phone);
	
	Page<DzhggEntity> findByNameLikeAndPhoneLikeOrderByRecordtimeDesc( String name, String phone, Pageable pageable);
	int countByNameLikeAndPhoneLike(String name, String phone);
	
	Page<DzhggEntity> findByActivityOrderByRecordtimeDesc(String activity,  Pageable pageable);
	int countByActivity(String activity);
	
	Page<DzhggEntity> findByNameLikeOrderByRecordtimeDesc( String name, Pageable pageable);
	int countByNameLike(String name );
	
	Page<DzhggEntity> findByPhoneLikeOrderByRecordtimeDesc(String phone, Pageable pageable);
	int countByPhoneLike(String phone);
}
