package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.UserBlackListImportEntity;

public interface UserBlacklistImportRepository extends PagingAndSortingRepository<UserBlackListImportEntity, Long> {
	Page<UserBlackListImportEntity> findBy(Pageable pageable);
	List<UserBlackListImportEntity> findBy();
	List<UserBlackListImportEntity> findByClientId(String clientId);
	
}
