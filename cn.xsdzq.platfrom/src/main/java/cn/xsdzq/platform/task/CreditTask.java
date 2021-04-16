package cn.xsdzq.platform.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;

@Component
public class CreditTask {

	
	@Autowired
	private MallUserService mallUserService;
	
	@Autowired
	private ParamService paramService;
	
	
	// 扫描失效积分，cron = "0 * 12 * * ? " 12点执行  ，cron = "0 0 0 * * ? " 0点执行
	@Scheduled(cron = "0 0 0 * * ? ") // 间隔5秒执行  cron = "0/5 * * * * ? "
    public void ScanCreditEndTask() {
		System.out.println("主机：0点扫描失效积分任务,begin: "+ DateUtil.DateToString(new Date()));
		//System.out.println("备机：0点扫描失效积分任务,begin: "+ DateUtil.DateToString(new Date()));
		ParamEntity paramEntity = paramService.getValueByCode("scanCreditEndTask");
		if("1".equals(paramEntity.getValue())) {
			//1-打开  0-关闭
			//积分自动失效
			mallUserService.endDateJob();
			System.out.println("主机：0点扫描失效积分任务,end: "+ DateUtil.DateToString(new Date()));
			//System.out.println("备机：0点扫描失效积分任务,end: "+ DateUtil.DateToString(new Date()));
			//卡券自动失效
			mallUserService.cardEndDateJob();
		}else{
			System.out.println("主机：0点扫描失效积分任务,开关已关闭。"+ DateUtil.DateToString(new Date()));
			//System.out.println("备机：0点扫描失效积分任务,开关已关闭。"+ DateUtil.DateToString(new Date()));
		};
		
    }
	//扫描crm接口
	@Scheduled(cron = "0 0 21 * * ? ") // 上午9点行 cron = "0 0 09 * * ? "
    public void ScanCrmTask() {
		//System.out.println("主机：21点扫描CRM积分明细任务,begin: "+ DateUtil.DateToString(new Date()));
		System.out.println("备机：21点扫描CRM积分明细任务,begin: "+ DateUtil.DateToString(new Date()));
		//检查开关是否开启
		ParamEntity paramEntity = paramService.getValueByCode("scanCrmTask");
		if("1".equals(paramEntity.getValue())) {
			//1-代开  0-关闭
			mallUserService.scanCrmCreditJob();//
			System.out.println("主机：21点扫描CRM积分明细任务,end: "+ DateUtil.DateToString(new Date()));
			//System.out.println("备机：21点扫描CRM积分明细任务,end: "+ DateUtil.DateToString(new Date()));
		}else{
			System.out.println("主机：21点扫描CRM积分明细任务,开关已关闭: "+ DateUtil.DateToString(new Date()));
			//System.out.println("备机：21点扫描CRM积分明细任务,开关已关闭: "+ DateUtil.DateToString(new Date()));
		};
		
    }
}
