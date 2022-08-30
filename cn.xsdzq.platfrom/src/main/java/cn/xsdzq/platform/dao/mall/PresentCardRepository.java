package cn.xsdzq.platform.dao.mall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.xsdzq.platform.entity.mall.PresentCardEntity;


public interface PresentCardRepository extends JpaRepository<PresentCardEntity, Long> {
	PresentCardEntity findByCardId(String cardId);

	List<PresentCardEntity> findByExpiryTimeGreaterThanEqualAndExpiryTimeLessThanEqualAndConvertStatus(int startDate, int endDate, int convertStatus);
}
