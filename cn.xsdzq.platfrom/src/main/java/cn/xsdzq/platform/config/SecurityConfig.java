package cn.xsdzq.platform.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cn.xsdzq.platform.transition.XsdPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// auth.inMemoryAuthentication().passwordEncoder(new
		// XsdPasswordEncoder()).withUser("user").password("password").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new XsdPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// http.formLogin().loginPage("/static/login.html").and().authorizeRequests().antMatchers("/hello/say")
		// .hasRole("USER").anyRequest().permitAll();
		// http.csrf().disable();
		http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login").and()
				.authorizeRequests().antMatchers("/hello/say").hasRole("USER").anyRequest().permitAll();
	}

}
