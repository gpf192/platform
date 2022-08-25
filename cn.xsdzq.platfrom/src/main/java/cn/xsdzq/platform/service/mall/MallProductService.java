package cn.xsdzq.platform.service.mall;

import cn.xsdzq.platform.entity.mall.MallProductEntity;
import cn.xsdzq.platform.model.mall.ProductQueryDTO;
import cn.xsdzq.platform.model.mall.ProductSaveDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MallProductService {
    void deleteByIds(List<Long> ids);

    void save(ProductSaveDTO productSaveDTO);

    Page<MallProductEntity> queryByPage(ProductQueryDTO productQueryDTO);
}
