package cn.xsdzq.platform.dao.mall;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.MallUserEntity;


public interface MallUserRepository extends JpaRepository<MallUserEntity, Long> {

	MallUserEntity findByClientId(String clientId);

}
