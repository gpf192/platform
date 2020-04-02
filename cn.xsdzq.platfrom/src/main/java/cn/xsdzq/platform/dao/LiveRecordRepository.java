package cn.xsdzq.platform.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.LiveRecordEntity;

public interface LiveRecordRepository extends PagingAndSortingRepository<LiveRecordEntity, Long> {

}
