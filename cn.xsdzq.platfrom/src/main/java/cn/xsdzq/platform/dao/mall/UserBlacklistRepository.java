package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.mall.UserBlackListEntity;

public interface UserBlacklistRepository  extends PagingAndSortingRepository<UserBlackListEntity, Long> {
	Page<UserBlackListEntity>  findByOrderByCreateDateDesc(Pageable pageable);
	
	Page<UserBlackListEntity>  findByClientIdLikeOrderByCreateDateDesc(String clientId, Pageable pageable);
	int countByClientIdLike(String clientId);
	
	Page<UserBlackListEntity>  findByClientNameLikeOrderByCreateDateDesc(String clientName, Pageable pageable);
	int countByClientNameLike(String clientName);
	
	Page<UserBlackListEntity>  findByClientIdLikeAndClientNameLikeOrderByCreateDateDesc(String clientId, String ClientName, Pageable pageable);
	int countByClientIdLikeAndClientNameLike(String clientId, String clientName);
	
	UserBlackListEntity findById(long id);
	List<UserBlackListEntity>  findByClientId(String clientId);

}
