package com.matrix.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.matrix.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
//	@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index() { // 컨트롤러에서 세션을 어떻게 찾는지?
		// /WEB-INF/views/idnex.jsp
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index";
	}
	
	// USER권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
