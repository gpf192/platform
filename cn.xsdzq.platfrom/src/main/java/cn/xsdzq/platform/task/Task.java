package cn.xsdzq.platform.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.xsdzq.platform.entity.mall.CreditRecordEntity;
import cn.xsdzq.platform.service.mall.CreditImportRecordService;
import cn.xsdzq.platform.service.mall.CreditImportTempService;
import cn.xsdzq.platform.service.mall.CreditService;
import cn.xsdzq.platform.service.mall.MallUserService;
import cn.xsdzq.platform.util.DateUtil;

@Component
public class Task {
	@Autowired
	@Qualifier("creditServiceImpl")
	private CreditService creditService;
	
	
	@Autowired
	@Qualifier("creditImportRecordServiceImpl")
	private CreditImportRecordService creditImportRecordService;
	
	@Autowired
	@Qualifier("creditImportTempServiceImpl")
	private CreditImportTempService creditImportTempService;
	
	@Autowired
	private MallUserService mallUserService;
	
	
	@Scheduled(cron = "0/59 * * * * ? ") // 间隔5秒执行
    public void ScanCreditTask() {
		System.out.println("使用SpringMVC框架配置定时任务");
		String now = DateUtil.Dateymd(new Date());
		System.out.println("今天是 "+now);
		List<CreditRecordEntity> recordList = creditImportRecordService.findByType(true);
		for(CreditRecordEntity record:recordList) {			
			if( now.equals(record.getEndDate())) {
				System.out.println("这一条符合 "+record.toString());
				
			}
			
		}
        
    }
}
