package com.matrix.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)

@RestController
public class HttpController {
	
	private static final String TAG = "HttpController:";
	
	// http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member.MemberBuilder().username("ssar").password("1234").build();
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("김길동");
		System.out.println(TAG + "setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	// http://localhost:8080/http/get?id=1 -> ?id=-1 부분을 query string이라고 한다
	// 인터넷 브라우저 요청은 무조건 GET요청 밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	// 파라미터를 받으려면 @RequestParam 어노테이션 사용
	@GetMapping("/http/get")
	public String getTest(Member m) { //?id=1&username=ssar&password=1234&email=ssar@nate.com
										// MessageConverter(스프링부트)가 자동으로 처리 -> 한 번에 받는 방법
		return "get 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}

	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")	// text/plain -> raw data, application/json
	public String postTest(@RequestBody Member m) {	// MessageConverter(스프링부트)
//		return "post 요청 : " + text; // raw data, text/plain
		return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	// @RequestBody 어노테이션을 사용하면 매우 간단하게 body에 실어보낸 data를 object로 mapping해서 받을 수 있다. 
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
