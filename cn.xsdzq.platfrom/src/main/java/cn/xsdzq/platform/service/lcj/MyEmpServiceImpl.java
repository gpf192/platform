package cn.xsdzq.platform.service.lcj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.lcj.MyEmpRepository;
import cn.xsdzq.platform.entity.lcj.EmpEntity;

@Service(value = "myEmpServiceImpl")
@Transactional(readOnly = true)
public class MyEmpServiceImpl implements MyEmpService{
	@Autowired
	private MyEmpRepository myEmpRepository;
	@Override
	public List<EmpEntity> getAllEmp(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByOrderById(pageRequest);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return (int)myEmpRepository.count();
	}
	@Override
	public int countEmpEntityByEmp_nameAndEmp_codeAndSales_department(String emp_name, String emp_code,
			String sales_department) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpNameAndEmpCodeAndDepartmentCode(emp_name, emp_code, sales_department);
	}
	@Override
	public List<EmpEntity> findByEmp_nameAndEmp_codeAndSales_departmentOrderByEmp_code(String emp_name, String emp_code,
			String sales_department, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpNameAndEmpCodeAndDepartmentCodeOrderByEmpCode(emp_name, emp_code, sales_department, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityByEmp_name(String emp_name) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpName(emp_name);
	}
	@Override
	public List<EmpEntity> findEmpEntityByEmp_nameOrderByEmp_code(String emp_name, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpNameOrderByEmpCode(emp_name, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityByEmp_code(String emp_code) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpCode(emp_code);
	}
	@Override
	public List<EmpEntity> findEmpEntityByEmp_codeOrderByEmp_code(String emp_code, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpCodeOrderByEmpCode(emp_code, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityBySales_department(String sales_department) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByDepartmentCode(sales_department);
	}
	@Override
	public List<EmpEntity> findEmpEntityBySales_departmentOrderByEmp_code(String sales_department, int pageNumber,
			int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByDepartmentCodeOrderByEmpCode(sales_department, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityByEmp_nameAndEmp_code(String emp_name, String emp_code) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpNameAndEmpCode(emp_name, emp_code);
	}
	@Override
	public List<EmpEntity> findEmpEntityByEmp_nameAndEmp_codeOrderByEmp_code(String emp_name, String emp_code,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpNameAndEmpCodeOrderByEmpCode(emp_name, emp_code, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityByEmp_nameAndSales_department(String emp_name, String sales_department) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpNameAndDepartmentCode(emp_name, sales_department);
	}
	@Override
	public List<EmpEntity> findEmpEntityByEmp_nameAndSales_departmentOrderByEmp_code(String emp_name,
			String sales_department, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpNameAndDepartmentCodeOrderByEmpCode(emp_name, sales_department, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
	@Override
	public int countEmpEntityByEmp_codeAndSales_department(String emp_code, String sales_department) {
		// TODO Auto-generated method stub
		return myEmpRepository.countByEmpCodeAndDepartmentCode(emp_code, sales_department);
	}
	@Override
	public List<EmpEntity> findEmpEntityByEmp_codeAndSales_departmentOrderByEmp_code(String emp_code,
			String sales_department, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		PageRequest pageable = new PageRequest(pageNumber, pageSize);
		Page<EmpEntity> pages = myEmpRepository.findByEmpCodeAndDepartmentCodeOrderByEmpCode(emp_code, sales_department, pageable);
		List<EmpEntity> infos = pages.getContent();
		return infos;
	}
}
