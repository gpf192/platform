package cn.xsdzq.platform.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.xsdzq.platform.dao.UserRepository;
import cn.xsdzq.platform.entity.AuthorityEntity;
import cn.xsdzq.platform.entity.RoleEntity;
import cn.xsdzq.platform.entity.UserEntity;

public class MyUserService implements UserDetailsService {
	Logger logger = LogManager.getLogger(MyUserService.class.getName());

	private UserRepository userRepository;

	public MyUserService() {
		// TODO Auto-generated constructor stub
	}

	public MyUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException, AuthenticationServiceException {
		// TODO Auto-generated method stub
		UserEntity userEntity = null;
			try {
				userEntity = userRepository.findUserByName(username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new AuthenticationServiceException("用户名或密码错误!");
			}
		

		if (userEntity == null) {
			//throw new UsernameNotFoundException(username + " not find");
			//System.out.println("chabudeded ");
			throw new AuthenticationServiceException("用户名或密码错误!");
		}
		
		if(userEntity.getLockFlag() >= 5){
			throw  new LockedException("您登录的错误次数过多，账户已锁定");
		}
		List<AuthorityEntity> authorities = getAuthorityList(userEntity);
		logger.info("getUsername" + userEntity.getUsername() + "; " + "getPassword" + userEntity.getPassword());
		User user = new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
		return user;
	}

	public List<AuthorityEntity> getAuthorityList(UserEntity userEntity) {
		// 第一，得到用户的角色
		Set<RoleEntity> roleEntities = userEntity.getRoleEntities();
		// 第二，得到所有权限
		Iterator<RoleEntity> roleIterable = roleEntities.iterator();
		List<AuthorityEntity> authorityEntitiesList = new ArrayList<AuthorityEntity>();
		while (roleIterable.hasNext()) {
			RoleEntity rEntity = roleIterable.next();
			Set<AuthorityEntity> authorityEntities = rEntity.getAuthorityEntities();
			Iterator<AuthorityEntity> authoritIterable = authorityEntities.iterator();
			while (authoritIterable.hasNext()) {
				AuthorityEntity authorityEntity = (AuthorityEntity) authoritIterable.next();
				authorityEntitiesList.add(authorityEntity);
			}
		}
		return authorityEntitiesList;
	}

}
