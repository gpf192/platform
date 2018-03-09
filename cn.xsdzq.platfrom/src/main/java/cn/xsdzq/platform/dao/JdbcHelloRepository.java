package cn.xsdzq.platform.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import cn.xsdzq.platform.entity.HelloEntity;

@Repository
public class JdbcHelloRepository implements HelloRepository {
	private JdbcOperations jdbcOperations;

	@Autowired
	public JdbcHelloRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;

	}

	@SuppressWarnings("deprecation")
	public void addHello() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM user";
		int count = jdbcOperations.queryForInt(sql);
		System.out.println(count);

	}

	@Override
	public void addHello(HelloEntity helloEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public HelloEntity findOne() {
		// TODO Auto-generated method stub
		return null;
	}

}
