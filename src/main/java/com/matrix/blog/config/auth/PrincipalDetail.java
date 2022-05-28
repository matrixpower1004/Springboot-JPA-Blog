package com.matrix.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.matrix.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다. -> UserDeails 타입의 PrincipalDetail 타입이 저장됨
// PrincipalDetail 타입이 저장될 때 우리가 DB에 저장한 User 객체도 댱연히 포함이 되어있어야 한다.

public class PrincipalDetail implements UserDetails{
	
	private User user; // 콤포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 여부 체크하여 리턴 (true:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다 (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴한다.(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고 있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한 개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();}); // ROLE_USER
		return collectors;

		//		collectors.add(new GrantedAuthority() {
//			
//			// 자바는 메소드르 이렇게 넣을 수 없다. 자바는 오브젝트를 담을 수 있지만 메소드만 넘길수는 없다.
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole(); // spring에서 role을 받을때의 규칙. prefix ROLE_ 는 꼭 넣어줘야 함.
//				// ROLE_USER
//			}
//		});
		
	}
	
}
