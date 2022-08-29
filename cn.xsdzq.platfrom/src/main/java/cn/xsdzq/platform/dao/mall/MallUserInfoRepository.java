package cn.xsdzq.platform.dao.mall;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface MallUserInfoRepository extends JpaRepository<MallUserInfoEntity, Long> {

	MallUserInfoEntity findByMallUserEntity(MallUserEntity mallUserEntity);
	MallUserInfoEntity findByClientId(String clientId);

	@Query(value = "update mall_user_info set frozen_integral=frozen_integral-?2 where client_id=?1 and frozen_integral>=?2", nativeQuery = true)
	@Modifying
	int reduceFrozenIntegral(String clientId, Integer frozenIntegral);

	@Query(value = "update mall_user_info set credit_score=credit_score-?2 where client_id=?1 and credit_score>=?2", nativeQuery = true)
	@Modifying
	int reduceUsableIntegral(String clientId, Integer creditScore);
}
