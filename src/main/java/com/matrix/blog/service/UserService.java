package com.matrix.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matrix.blog.model.User;
import com.matrix.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void memberJoin(User user) {
		userRepository.save(user);
	}

	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	public User memberLogin(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		// 이 안에서 여러번 select 하더라도 같은 데이터가 찾아짐
	}
// 		아래 코드는 중복값이 요청될 경우 버그 발생 
//		try {
//			userRepository.save(user);
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService:회원가입():" + e.getMessage());
//		}
//		return -1;
}
