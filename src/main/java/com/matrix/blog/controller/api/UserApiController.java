package com.matrix.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.blog.dto.ResponseDto;
import com.matrix.blog.model.RoleType;
import com.matrix.blog.model.User;
import com.matrix.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired 
	private UserService userService;
	
//	@Autowired
//	private HttpSession session; // 세션 객체를 스프링 컨테이너가 bean으로 등록해서 가지고 있도록
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user, HttpSession session) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		userService.memberJoin(user); // 1이면 성공. -1이면 실패
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
		// 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 라이브러리)
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 스프링 시큐리티 이용해서 로그인을 하면 아래의 방식을 사용하지 않음. 기본적인 로그인 로직 개념을 파악할 것. 
//	@PostMapping("api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
	// HttpSession을 DI로 설정하고 매개변수는 생략할 수 있다. 
//	public ResponseDto<Integer> login(@RequestBody User user) {
//		System.out.println("UserApiController : login 호출됨");
//		User principal = userService.memberLogin(user); // principal(접근주체)
//		
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
