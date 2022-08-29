package cn.xsdzq.platform.controller.mall;

import cn.xsdzq.platform.constants.AdjustmentTypeEnum;
import cn.xsdzq.platform.constants.ChengQuanOrderStatusEnum;
import cn.xsdzq.platform.constants.OrderStatusEnum;
import cn.xsdzq.platform.entity.mall.MallOrderEntity;
import cn.xsdzq.platform.model.Pagination;
import cn.xsdzq.platform.model.mall.OrderQueryDTO;
import cn.xsdzq.platform.model.mall.OrderRecordDTO;
import cn.xsdzq.platform.model.mall.OrderSaveDTO;
import cn.xsdzq.platform.model.mall.StatusEnumDTO;
import cn.xsdzq.platform.service.mall.OrderService;
import cn.xsdzq.platform.util.GsonUtil;
import cn.xsdzq.platform.util.StatusEnumUtil;
import org.springframework.beans.BeanUtils;
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

@RestController
@RequestMapping(value = "/mall/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping(value = "/add")
    public Map<String, Object> addProduct(@RequestBody OrderSaveDTO dto) {
        if (dto == null || StringUtils.isEmpty(dto.getId())
                || StringUtils.isEmpty(dto.getAdjustmentType())
                || StringUtils.isEmpty(dto.getAdjustmentReason())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }
        orderService.save(dto);
        return GsonUtil.buildMap(0, "success", null);
    }

    @GetMapping(value = "/all")
    public Map<String, Object> getAllPage(HttpServletRequest request, OrderQueryDTO orderQueryDTO) {
        if (orderQueryDTO == null || StringUtils.isEmpty(orderQueryDTO.getPageNumber()) || StringUtils.isEmpty(orderQueryDTO.getPageSize())) {
            return GsonUtil.buildMap(1, "必输项为空", null);
        }

        Page<MallOrderEntity> page = orderService.queryByPage(orderQueryDTO);
        List<MallOrderEntity> content = page.getContent();

        List<OrderRecordDTO> orderRecordDTOList = new ArrayList<>(Collections.emptyList());
        if (!CollectionUtils.isEmpty(content)) {
            for (MallOrderEntity order : content) {
                OrderRecordDTO orderRecordDTO = new OrderRecordDTO();
                BeanUtils.copyProperties(order, orderRecordDTO);
                orderRecordDTO.setOrderStatusName(OrderStatusEnum.match(order.getOrderStatus()).getDesc());
                orderRecordDTO.setRechargeStatusName(ChengQuanOrderStatusEnum.match(order.getRechargeStatus()).getDesc());
                orderRecordDTO.setAdjustmentTypeName(AdjustmentTypeEnum.match(order.getAdjustmentType()).getDesc());
                orderRecordDTOList.add(orderRecordDTO);
            }
        }
        Pagination pagination = new Pagination(orderQueryDTO.getPageNumber(), orderQueryDTO.getPageSize(), (int) page.getTotalElements());
        return GsonUtil.buildMap(0, "ok", orderRecordDTOList, pagination);
    }

    @GetMapping("/orderstatus")
    public Map<String, Object> getOrderStatus() {
        List<StatusEnumDTO> statusEnumDTO = StatusEnumUtil.getStatusEnumDTO(OrderStatusEnum.class);
        return GsonUtil.buildMap(0, "success", statusEnumDTO);
    }

    @GetMapping("/rechargestatus")
    public Map<String, Object> getRechargeStatus() {
        List<StatusEnumDTO> statusEnumDTO = StatusEnumUtil.getStatusEnumDTO(ChengQuanOrderStatusEnum.class);
        return GsonUtil.buildMap(0, "success", statusEnumDTO);
    }

    @GetMapping("/adjustmenttype")
    public Map<String, Object> getAdjustmentType() {
        List<StatusEnumDTO> statusEnumDTO = StatusEnumUtil.getStatusEnumDTO(AdjustmentTypeEnum.class);
        return GsonUtil.buildMap(0, "success", statusEnumDTO);
    }
}
