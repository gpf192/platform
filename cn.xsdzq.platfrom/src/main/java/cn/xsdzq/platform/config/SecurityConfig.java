package cn.xsdzq.platform.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import cn.xsdzq.platform.dao.AuthorityRepository;
import cn.xsdzq.platform.dao.UserRepository;
import cn.xsdzq.platform.handler.MyAuthenctiationFailureHandler;
import cn.xsdzq.platform.security.AjaxAuthenticationEntryPoint;
import cn.xsdzq.platform.security.MyAccessDecisionManager;
import cn.xsdzq.platform.security.MyFilterInvocationSecurityMetadataSource;
import cn.xsdzq.platform.security.MyUserService;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// auth.inMemoryAuthentication().passwordEncoder(new
		// XsdPasswordEncoder()).withUser("user").password("password").roles("USER");
		// auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new
		// XsdPasswordEncoder()); //数据库认证
		// 自定义认证
		// auth.userDetailsService(new
		// MyUserService(userRepository)).passwordEncoder(new XsdPasswordEncoder());
		auth.userDetailsService(new MyUserService(userRepository)).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// http.formLogin().loginPage("/static/login.html").and().authorizeRequests().antMatchers("/hello/say")
		// .hasRole("USER").anyRequest().permitAll();
		// http.csrf().disable();
		/*
		 * http.formLogin().usernameParameter("username").passwordParameter("password").
		 * loginPage("/login").and()
		 * .authorizeRequests().antMatchers("/static/index.html").hasRole("USER").
		 * anyRequest().permitAll().and() .headers().frameOptions().disable()//add by
		 * fanjx 解除浏览器对框架的限制 .and() .csrf().disable();
		 */

		// http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login").and()
		// .authorizeRequests().antMatchers("/static/index.html").hasRole("USER").anyRequest().permitAll().and()
		// .csrf().disable();
		// http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login").and()
		// .authorizeRequests().anyRequest().permitAll().and().csrf().disable();

		// 动态配置
		// add by fjx begin
		// http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login")
		// .defaultSuccessUrl("/static/index.html").and().logout().logoutSuccessUrl("/login").and()
		// .authorizeRequests().and().headers().frameOptions().disable();
		// add by fjx end  failureHandler(myAuthenctiationFailureHandler)
		http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login")
				.defaultSuccessUrl("/static/index.html").failureHandler(myAuthenctiationFailureHandler).and().logout().logoutSuccessUrl("/login").and()
				.authorizeRequests().anyRequest().authenticated()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						// TODO Auto-generated method
						object.setSecurityMetadataSource(mySecurityMetadataSource());
						object.setAccessDecisionManager(myAccessDecisionManager());
						return object;
					}
				}).and().headers().frameOptions().disable().and().exceptionHandling()
				.authenticationEntryPoint(gAuthenticationEntryPoint()).and().csrf()
				.disable();
		// 限制只允许一个用户登录
		http.sessionManagement().maximumSessions(1).expiredUrl("/login/?expire=true");

	}

	@Bean
	public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
		return new MyFilterInvocationSecurityMetadataSource(authorityRepository);
	}

	@Bean
	public AccessDecisionManager myAccessDecisionManager() {
		return new MyAccessDecisionManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new UserAuditorAware();
	}

	@Bean
	public AuthenticationEntryPoint gAuthenticationEntryPoint() {
		return new AjaxAuthenticationEntryPoint();
	}

}
