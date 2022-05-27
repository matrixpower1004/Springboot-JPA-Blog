package com.matrix.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController는 문자열 자체를 return했다면 @Controller는 해당경로 이하에 있는 파일을 리턴
// 1. Springboot는 기본적으로 jsp를 지원하지 않기 때문에 jsp파일을 리턴한다고 해도 동작하지 않음
// 		=> pom.xml에 의존성 설정을 해줘야 jsp파일이 동작할 수 있다. (jasper)
// 2. 기본경로가 static이기 때문에 이 경로에다가 jsp 파일을 놓으면 제대로 인식하지 못한다.
//		=> static는 스프링이 정적 파일을 놓는 경로 : 브라우저가 인식 할 수 있는 파일들만 놓아야 한다.
//		=> 사진, html파일 등
//		=> jsp는 컴파일이 필요한 동적인 파일이라 브라우저가 인식하지 못함.
// 3. yml파일의 spring: 부분 수정
//		=> prefix:/WEB/-INF/views/ - 컨트롤러가 리턴을 할 때 앞에 붙여주는 경로명
//		=> suffix:.jsp - 컨트롤러가 리턴을 할 때 뒤에 붙여주는 경로명

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로: src/main/resources/static
		// 리턴명 : /home.html
		// 풀경로: src/main/resoureces/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
//	    prefix: /WEB-INF/views/
//	    suffix: .jsp
		// 풀경로:/WEB-INF/views/test.jsp.jsp
		return "test";	// suffix가 설정되어 있으므로 뒤에 .jsp 확장자 생략
	}
}
