package cn.xsdzq.platform.controller.mall;

import cn.xsdzq.platform.constants.BrandStatusEnum;
import cn.xsdzq.platform.entity.mall.MallBrandEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.*;
import cn.xsdzq.platform.service.mall.BrandService;
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
@RequestMapping(value = "/mall/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    @PostMapping(value = "/add")
    public Map<String, Object> addBrand(@RequestBody BrandSaveDTO dto) {
        if (dto == null || StringUtils.isEmpty(dto.getGoodsTypeId()) || StringUtils.isEmpty(dto.getSellStatus())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        brandService.save(dto);
        return GsonUtil.buildMap(0, "success", null);
    }

    @GetMapping("/sellstatus")
    public Map<String, Object> getBrandSellStatus() {
        ArrayList<BrandSellStatusDTO> brandSellStatusDTOList = new ArrayList<>();
        for (BrandStatusEnum bse : BrandStatusEnum.values()) {
            BrandSellStatusDTO bssDTO = new BrandSellStatusDTO();
            bssDTO.setCode(bse.getCode().toString());
            bssDTO.setDesc(bse.getDesc());
            brandSellStatusDTOList.add(bssDTO);
        }

        BrandSellStatusDTO bssDTO = new BrandSellStatusDTO();
        bssDTO.setCode("all");
        bssDTO.setDesc("全部");
        brandSellStatusDTOList.add(bssDTO);
        return GsonUtil.buildMap(0, "success", brandSellStatusDTOList);
    }

    @GetMapping(value = "/all")
    public Map<String, Object> getAllPage(HttpServletRequest request, BrandQueryDTO brandQueryDTO) {
        if (brandQueryDTO == null || StringUtils.isEmpty(brandQueryDTO.getPageNumber()) || StringUtils.isEmpty(brandQueryDTO.getPageSize())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        Page<MallBrandEntity> page = brandService.queryByPage(brandQueryDTO);
        List<MallBrandEntity> content = page.getContent();

        List<BrandRecordDTO> brandRecordDTOList = Collections.emptyList();
        if (!CollectionUtils.isEmpty(content)) {
            brandRecordDTOList = BeanHelper.copyList(content, BrandRecordDTO.class);
        }
        Pagination pagination = new Pagination(brandQueryDTO.getPageNumber(), brandQueryDTO.getPageSize(), (int) page.getTotalElements());
        return GsonUtil.buildMap(0, "ok", brandRecordDTOList, pagination);

    }

    @RequestMapping(value = "/delete", method = POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> deleteBrand(HttpServletRequest request, @RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        brandService.deleteByIds(ids);
        return GsonUtil.buildMap(0, "success", null);
    }

}
