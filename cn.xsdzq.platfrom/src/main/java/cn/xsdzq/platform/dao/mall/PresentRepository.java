package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.PresentEntity;



public interface PresentRepository extends JpaRepository<PresentEntity, Long> {
	List<PresentEntity> findByName(String name);
	PresentEntity findByCode(String code);
	List<PresentEntity> findByCategoryId(long categoryId);
}
