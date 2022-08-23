package cn.xsdzq.platform.service.mall.impl;

import cn.xsdzq.platform.dao.mall.BrandRepository;
import cn.xsdzq.platform.entity.mall.MallBrandEntity;
import cn.xsdzq.platform.model.mall.BrandQueryDTO;
import cn.xsdzq.platform.model.mall.BrandSaveDTO;
import cn.xsdzq.platform.service.mall.BrandService;
import cn.xsdzq.platform.util.BeanHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandRepository brandRepository;

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            brandRepository.deleteById(id);
        }
    }

    @Override
    public void save(BrandSaveDTO brandDTO) {
        MallBrandEntity saveOrUpdateBrand = new MallBrandEntity();
        BeanUtils.copyProperties(brandDTO, saveOrUpdateBrand);
        if (brandDTO.getId() != null) {
            Optional<MallBrandEntity> optional = brandRepository.findById(brandDTO.getId());
            if (!optional.isPresent()) {
                throw new RuntimeException("更新数据不存在");
            } else {
                MallBrandEntity mallBrandEntity = optional.get();
                BeanHelper.copyPropertiesIgnoreNull(saveOrUpdateBrand, mallBrandEntity);
                brandRepository.save(mallBrandEntity);
                return;
            }
        }

        brandRepository.save(saveOrUpdateBrand);
    }

    @Override
    public Page<MallBrandEntity> queryByPage(BrandQueryDTO brandQueryDTO, Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        MallBrandEntity brand = new MallBrandEntity();
        BeanUtils.copyProperties(brandQueryDTO, brand);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("sellStatus", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("goodsTypeId", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MallBrandEntity> example = Example.of(brand, matcher);
        return brandRepository.findAll(example, pageable);
    }

}
