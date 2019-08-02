package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.EmpRepository;
import cn.xsdzq.platform.dao.lcj.PrizeRepository;
import cn.xsdzq.platform.entity.lcj.EmpEntity;
import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;
import cn.xsdzq.platform.entity.lcj.PrizeEntity;
import cn.xsdzq.platform.service.InfoServiceImpl;


@Service(value = "empServiceImpl")
@Transactional(readOnly = true)
public class EmpServiceImpl implements EmpService{
	Logger logger = LogManager.getLogger(EmpServiceImpl.class.getName());
	@Autowired
	private EmpRepository empRepository;
	
	@Override
	public List<EmpEntity> getAllPrize() {
		// TODO Auto-generated method stub
		List<EmpEntity> infos = empRepository.getAllEmp();
		return infos;
	}

	@Override
	@Transactional
	public void addEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		EmpEntity en = empRepository.getEmpByEmpCode(entity.getEmpCode()) ;
		if(en == null) {
			empRepository.addEmp(entity);
			EmpTicketEntity et = new EmpTicketEntity();	
			et.setEmpEntity(entity);
			empRepository.addEmpTicket(et);
		}else {
			//删除的员工又重新添加 
			// empRepository.addEmpAgain(entity);
			entity.setId(en.getId());
			entity.setEnable(1);
			System.out.println("yuangongyicunzai __________________"+ entity.toString());

			empRepository.modifyEmp(entity);
		}

	}

	@Override
	@Transactional
	public void deleteEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		empRepository.deleteEmp(entity);
	}

	@Override
	@Transactional
	public void modifyEmp(EmpEntity entity) {
		// TODO Auto-generated method stub
		empRepository.modifyEmp(entity);
	}

}
