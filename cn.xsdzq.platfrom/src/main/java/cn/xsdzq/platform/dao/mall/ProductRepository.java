package cn.xsdzq.platform.dao.mall;


import cn.xsdzq.platform.entity.mall.MallProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<MallProductEntity, Long> {


}