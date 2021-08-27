package cn.xsdzq.platform.task;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.lcj.ParamEntity;
import cn.xsdzq.platform.service.lcj.ParamService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;

@PropertySource("classpath:/param.ini")
@Component
public class CreditTask {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
		} //使spring能够识别${cron} 
	
	@Autowired
	private MallUserService mallUserService;
	
	@Autowired
	private ParamService paramService;
	
	
	// 扫描失效积分，以及卡券自动失效cron = "0 * 12 * * ? " 12点执行  ，cron = "0 0 0 * * ? " 0点执行
	//@Scheduled(cron = "0 0 0 * * ? ") // 间隔5秒执行  cron = "0/5 * * * * ? "
	@Scheduled(cron="${creditEndCron}")
    public void ScanCreditEndTask() {
		System.out.println("主机：0点扫描失效积分任务,begin: "+ DateUtil.DateToString(new Date()));
		//System.out.println("备机：0点扫描失效积分任务,begin: "+ DateUtil.DateToString(new Date()));
		ParamEntity paramEntity = paramService.getValueByCode("scanCreditEndTask");
		if("1".equals(paramEntity.getValue())) {
			//1-打开  0-关闭
			//积分自动失效
			mallUserService.endDateJob();
			System.out.println("主机：0点扫描失效积分任务,end: "+ DateUtil.DateToString(new Date()));
			//卡券自动失效
			mallUserService.cardEndDateJob();
		}else{
			System.out.println("主机：0点扫描失效积分任务,开关已关闭。"+ DateUtil.DateToString(new Date()));
		};
		
    }
	//扫描crm接口
	//@Scheduled(cron = "0 0 21 * * ? ") // 上午9点行 cron = "0 0 09 * * ? "
    @Scheduled(cron="${crmCron}")
    public void ScanCrmTask() {
		//System.out.println("主机：21点扫描CRM积分明细任务,begin: "+ DateUtil.DateToString(new Date()));
		System.out.println("主机：21点同步CRM积分明细任务,begin: "+ DateUtil.DateToString(new Date()));
		//检查开关是否开启
		ParamEntity paramEntity = paramService.getValueByCode("scanCrmTask");
		if("1".equals(paramEntity.getValue())) {
			//1-代开  0-关闭
			mallUserService.scanCrmCreditJob();//
			System.out.println("主机：21点同步CRM积分明细任务,end: "+ DateUtil.DateToString(new Date()));
		}else{
			System.out.println("主机：21点同步CRM积分明细任务,开关已关闭: "+ DateUtil.DateToString(new Date()));
		};
		
    }
	
	//每日邮件发送
	//@Scheduled(cron = "0 0 05 * * ? ") // 上午9点行 cron = "0 0 05 * * ? "//
	@Scheduled(cron="${mailCron}")
    public void mailSendTask() {
		System.out.println("主机：邮件发送-凌晨5点扫描CRM积分明细任务,begin: "+ DateUtil.DateToString(new Date()));
		//检查开关是否开启
		ParamEntity paramEntity = paramService.getValueByCode("sendMailJob");
		if("1".equals(paramEntity.getValue())) {
			//1-代开  0-关闭
			mallUserService.mailSendCreditJob();//
			System.out.println("主机：邮件发送-凌晨5点扫描CRM积分明细任务,end: "+ DateUtil.DateToString(new Date()));
		}else{
			System.out.println("主机：邮件发送-凌晨5点扫描CRM积分明细任务,开关已关闭: "+ DateUtil.DateToString(new Date()));
		};
		
    }
}
