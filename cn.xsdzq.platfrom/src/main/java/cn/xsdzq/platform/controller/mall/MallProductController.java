package cn.xsdzq.platform.controller.mall;

import cn.xsdzq.platform.constants.ProductStatusEnum;
import cn.xsdzq.platform.entity.mall.MallProductEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.ProductQueryDTO;
import cn.xsdzq.platform.model.mall.ProductRecordDTO;
import cn.xsdzq.platform.model.mall.ProductSaveDTO;
import cn.xsdzq.platform.model.mall.ProductSellStatusDTO;
import cn.xsdzq.platform.service.mall.MallProductService;
import cn.xsdzq.platform.util.BeanHelper;
import cn.xsdzq.platform.util.GsonUtil;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/mall/product")
public class MallProductController {
    @Resource
    private MallProductService mallProductService;

    @PostMapping(value = "/add")
    public Map<String, Object> addProduct(@RequestBody ProductSaveDTO dto) {
        if (dto == null || StringUtils.isEmpty(dto.getGoodsNo())
                || StringUtils.isEmpty(dto.getOfficialPrice())
                || StringUtils.isEmpty(dto.getPrice())
                || StringUtils.isEmpty(dto.getSellStatus())
                || StringUtils.isEmpty(dto.getGoodsTypeId())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        mallProductService.save(dto);
        return GsonUtil.buildMap(0, "success", null);
    }

    @GetMapping("/sellstatus")
    public Map<String, Object> getProductSellStatus() {
        ArrayList<ProductSellStatusDTO> productSellStatusDTOList = new ArrayList<>();
        for (ProductStatusEnum pse : ProductStatusEnum.values()) {
            ProductSellStatusDTO pssDTO = new ProductSellStatusDTO();
            pssDTO.setCode(pse.getCode().toString());
            pssDTO.setDesc(pse.getDesc());
            productSellStatusDTOList.add(pssDTO);
        }

        ProductSellStatusDTO pssDTO = new ProductSellStatusDTO();
        pssDTO.setCode("all");
        pssDTO.setDesc("全部");
        productSellStatusDTOList.add(pssDTO);
        return GsonUtil.buildMap(0, "success", productSellStatusDTOList);
    }

    @GetMapping(value = "/all")
    public Map<String, Object> getAllPage(HttpServletRequest request, ProductQueryDTO productQueryDTO) {
        if (productQueryDTO == null || StringUtils.isEmpty(productQueryDTO.getPageNumber()) || StringUtils.isEmpty(productQueryDTO.getPageSize())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }
        Page<MallProductEntity> page = mallProductService.queryByPage(productQueryDTO);
        List<MallProductEntity> content = page.getContent();

        List<ProductRecordDTO> productRecordDTOList = Collections.emptyList();
        if (!CollectionUtils.isEmpty(content)) {
            productRecordDTOList = BeanHelper.copyList(content, ProductRecordDTO.class);
        }
        Pagination pagination = new Pagination(productQueryDTO.getPageNumber(), productQueryDTO.getPageSize(), (int) page.getTotalElements());
        return GsonUtil.buildMap(0, "ok", productRecordDTOList, pagination);
    }

    @RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> deleteProduct(HttpServletRequest request, @RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        mallProductService.deleteByIds(ids);
        return GsonUtil.buildMap(0, "success", null);
    }

}
