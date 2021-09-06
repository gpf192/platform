package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.MailItemListEntity;

public interface MailItemListRepository extends JpaRepository<MailItemListEntity, Long>{
	public List<MailItemListEntity> findByFlag(int flag);
}
