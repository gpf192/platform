package cn.xsdzq.platform.dao.mall;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.MallUserEntity;
import cn.xsdzq.platform.entity.mall.MallUserInfoEntity;



public interface MallUserInfoRepository extends JpaRepository<MallUserInfoEntity, Long> {

	MallUserInfoEntity findByMallUserEntity(MallUserEntity mallUserEntity);
	MallUserInfoEntity findByClientId(String clientId);
}
