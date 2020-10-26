package cn.xsdzq.platform.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.service.mall.MallUserService;

@Component
public class CreditTask {

	
	@Autowired
	private MallUserService mallUserService;
	
	// cron = "0 * 12 * * ? " 12点执行  ，cron = "0 0 0 * * ? " 0点执行
	@Scheduled(cron = "0 0 0 * * ? ") // 间隔5秒执行  cron = "0/5 * * * * ? "
    public void ScanCreditTask() {
		System.out.println("使用SpringMVC框架配置定时任务");
		//String now = DateUtil.Dateymd(new Date());
		System.out.println("今天是lingcheng "+ new Date());

		mallUserService.endDateJob();
		System.out.println("job done : "+new Date());
    }
	
	@Scheduled(cron = "0 * 5 * * ? ") // 间隔5秒执行  cron = "0/5 * * * * ? "
    public void ScanCrmTask() {
		System.out.println("CRM-使用SpringMVC框架配置定时任务");
		//String now = DateUtil.Dateymd(new Date());
		System.out.println("CRM: 今天是lingcheng "+ new Date());

		mallUserService.scanCrmCreditJob();
		System.out.println("CRM: job done : "+new Date());
    }
}
