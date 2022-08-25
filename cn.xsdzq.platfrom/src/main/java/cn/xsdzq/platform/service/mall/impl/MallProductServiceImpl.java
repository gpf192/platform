package cn.xsdzq.platform.service.mall.impl;

import cn.xsdzq.platform.dao.mall.BrandRepository;
import cn.xsdzq.platform.dao.mall.ProductRepository;
import cn.xsdzq.platform.entity.mall.MallBrandEntity;
import cn.xsdzq.platform.entity.mall.MallProductEntity;
import cn.xsdzq.platform.exception.BusinessException;
import cn.xsdzq.platform.model.mall.ProductQueryDTO;
import cn.xsdzq.platform.model.mall.ProductSaveDTO;
import cn.xsdzq.platform.service.mall.MallProductService;
import cn.xsdzq.platform.util.BeanHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class MallProductServiceImpl implements MallProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private BrandRepository brandRepository;

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public void save(ProductSaveDTO productDTO) {
        MallBrandEntity brand = brandRepository.findByGoodsTypeId(productDTO.getGoodsTypeId());
        if(brand == null){
            throw new BusinessException("商品分类不存在");
        }

        productDTO.setBrandId(brand.getId());
        MallProductEntity saveOrUpdateProduct = new MallProductEntity();
        BeanUtils.copyProperties(productDTO, saveOrUpdateProduct);
        if (productDTO.getId() != null) {
            Optional<MallProductEntity> optional = productRepository.findById(productDTO.getId());
            if (!optional.isPresent()) {
                throw new RuntimeException("更新数据不存在");
            } else {
                MallProductEntity mallProductEntity = optional.get();
                // 不允许修改字段
                saveOrUpdateProduct.setGoodsTypeId(null);
                saveOrUpdateProduct.setGoodsNo(null);
                saveOrUpdateProduct.setCreateTime(null);
                saveOrUpdateProduct.setUpdateTime(null);
                BeanHelper.copyPropertiesIgnoreNull(saveOrUpdateProduct, mallProductEntity);
                productRepository.save(mallProductEntity);
                return;
            }
        }

        productRepository.save(saveOrUpdateProduct);
    }

    @Override
    public Page<MallProductEntity> queryByPage(ProductQueryDTO productQueryDTO) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(productQueryDTO.getPageNumber(), productQueryDTO.getPageSize(), sort);

        MallProductEntity product = new MallProductEntity();
        BeanUtils.copyProperties(productQueryDTO, product);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("pageNumber", "pageSize")
                .withMatcher("goodsNo", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("sellStatus", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("goodsTypeId", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MallProductEntity> example = Example.of(product, matcher);
        return productRepository.findAll(example, pageable);
    }

}
