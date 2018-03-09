package cn.xsdzq.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xsdzq.platform.dao.JpaHelloRepository;
import cn.xsdzq.platform.entity.HelloEntity;

@Service(value = "helloServiceImpl")
@Transactional
public class HelloServiceImpl implements IHelloService {

	@Autowired
	private JpaHelloRepository jpaHelloRepository;

	@Override
	@Transactional
	public void addHello(HelloEntity helloEntity) {
		// TODO Auto-generated method stub
		jpaHelloRepository.addHello(helloEntity);

	}

}
