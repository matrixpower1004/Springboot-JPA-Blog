package com.matrix.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matrix.blog.model.User;
import com.matrix.blog.repository.UserRepository;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스피링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 함.
	// username이 DB에 있는지만 확인해주면 됨. -> loadUserByUsername
	@Override // 오버라이딩해서 구현하지 않으면 우리가 들고 있는 유저 정보를 담아줄 수 없음.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		// 유저정보가 맞으면
		// principal 정보가 없으면 아이디:user, 패스워드:콘솔창에 출력한 값이 되어버림
		return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 됨. 그때 타입이 UserDetails가 되는.
	}
}
