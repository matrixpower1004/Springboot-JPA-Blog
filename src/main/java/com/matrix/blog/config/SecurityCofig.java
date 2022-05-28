package com.matrix.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.matrix.blog.config.auth.PrincipalDetail;
import com.matrix.blog.config.auth.PrincipalDetailService;

// bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈등록(IoC 관리)
@EnableWebSecurity // 시큐리티 필터를 등록하고 스프링 부트 기본설정을 사용하지 않겠다는 의미.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityCofig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // Spring이 관리하는 IoC로 등록
	public BCryptPasswordEncoder encodePWD() {
//		String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 어떤 hash가 되어 회원가입이 되었는지 알아야
	// 같은 hash로 암호화해서 DB에 있는 hash와 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); // null 자리에 있는 object에게 알려줘야 함
	}
	
	// 필터 등록을 여기서
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는 게 좋음)
		.authorizeRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
			.permitAll() 
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청하는 로그인을 가로채서 대신 로그인 해준다.
			.defaultSuccessUrl("/");
//			.failureUrl("/fail"); // 만들지 않을 예정
	}
}
