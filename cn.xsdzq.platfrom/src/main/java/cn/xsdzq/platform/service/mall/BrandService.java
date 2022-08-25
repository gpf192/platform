package cn.xsdzq.platform.service.mall;

import cn.xsdzq.platform.entity.mall.MallBrandEntity;
import cn.xsdzq.platform.model.mall.BrandQueryDTO;
import cn.xsdzq.platform.model.mall.BrandSaveDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    void deleteByIds(List<Long> ids);

    void save(BrandSaveDTO brandDTO);

    Page<MallBrandEntity> queryByPage(BrandQueryDTO brandQueryDTO);
}
