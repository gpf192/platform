package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.VideoEntity;

public interface PageVideoRepository  extends PagingAndSortingRepository<VideoEntity, Long>{
	Page<VideoEntity> findByFundTypeOrderByCreatetimeDesc(int fundType ,Pageable pageable);
	int countByFundType(int fundType );
	Page<VideoEntity> findByOrderByCreatetimeDesc(Pageable pageable);
	List<VideoEntity> findByToogleAndFundType(boolean toogle, int fundType);
}
