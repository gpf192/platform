package cn.xsdzq.platform.service.mall.client.chengquan;

public interface DirectChargeService {

    CommonRespEntity<PayTelRespEntity> payTel(PayTelReqEntity telPayReqEntity);

    CommonRespEntity<GetOrderRespEntity> getOrder(GetOrderReqEntity orderGetReqEntity);
}
