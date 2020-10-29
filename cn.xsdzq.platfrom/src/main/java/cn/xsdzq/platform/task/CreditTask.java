package cn.xsdzq.platform.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.service.mall.MallUserService;

@Component
public class CreditTask {

	
	@Autowired
	private MallUserService mallUserService;
	
	@Autowired
	private ParamService paramService;
	
	
	// 扫描失效积分，cron = "0 * 12 * * ? " 12点执行  ，cron = "0 0 0 * * ? " 0点执行
	@Scheduled(cron = "0 0 0 * * ? ") // 间隔5秒执行  cron = "0/5 * * * * ? "
    public void ScanCreditEndTask() {
		System.out.println("使用SpringMVC框架配置定时任务"+ new Date());
		ParamEntity paramEntity = paramService.getValueByCode("scanCreditEndTask");
		if("1".equals(paramEntity.getValue())) {
			//1-代开  0-关闭
			mallUserService.endDateJob();
			System.out.println("job done : "+new Date());
		}else{
			System.out.println("ScanCreditEndTask 关闭");
		};
		
    }
	//扫描crm接口
	@Scheduled(cron = "0 21 16 * * ? ") // 上午10点行 cron = "0 0 10 * * ? "
    public void ScanCrmTask() {
		System.out.println("CRM-使用SpringMVC框架配置定时任务 "+ new Date());
		//检查开关是否开启
		ParamEntity paramEntity = paramService.getValueByCode("scanCrmTask");
		if("1".equals(paramEntity.getValue())) {
			//1-代开  0-关闭
			mallUserService.scanCrmCreditJob();
			System.out.println("CRM: job done : "+new Date());
		}else{
			System.out.println("crm-ScanCrmTask 关闭");
		};
		
    }
}
