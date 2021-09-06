package cn.xsdzq.platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.VideoEntity;


public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

	List<VideoEntity> findByToogleAndFundType(boolean toogle, int fundType);
	List<VideoEntity> findByFundTypeOrderByIdDesc( int fundType);

}
