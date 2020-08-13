package cn.xsdzq.platform.dao.lcj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.xsdzq.platform.entity.lcj.EmpTicketEntity;

public interface MyEmpTicketRepository  extends PagingAndSortingRepository<EmpTicketEntity, Long> {
	
	Page<EmpTicketEntity> findByOrderByWeightDescModifytimeDesc(Pageable pageable);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameLikeAndEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName, String empCode,String division, Pageable pageable);
	int countByEmpEntity_empNameLikeAndEmpEntity_empCodeAndEmpEntity_division(String empName, String empCode,String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameLikeOrderByWeightDescModifytimeDesc(String empName, Pageable pageable);
	int countByEmpEntity_empNameLike(String empName);
	
	Page<EmpTicketEntity> findByEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empCode, Pageable pageable);
	int countByEmpEntity_empCode(String empCode);
	
	Page<EmpTicketEntity> findByEmpEntity_divisionOrderByWeightDescModifytimeDesc(String division, Pageable pageable);
	int countByEmpEntity_division(String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empNameLikeAndEmpEntity_empCodeOrderByWeightDescModifytimeDesc(String empName, String empCode, Pageable pageable);
	int countByEmpEntity_empNameLikeAndEmpEntity_empCode(String empName, String empCode );
	
	Page<EmpTicketEntity> findByEmpEntity_empNameLikeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc(String empName,String division, Pageable pageable);
	int countByEmpEntity_empNameLikeAndEmpEntity_division(String empName,String division);
	
	Page<EmpTicketEntity> findByEmpEntity_empCodeAndEmpEntity_divisionOrderByWeightDescModifytimeDesc( String empCode,String division, Pageable pageable);
	int countByEmpEntity_empCodeAndEmpEntity_division( String empCode,String division);	
}
