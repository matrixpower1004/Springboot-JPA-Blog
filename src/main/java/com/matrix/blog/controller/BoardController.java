package com.matrix.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.matrix.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 컨트롤러에서 세션을 어떻게 찾는지?
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC) Pageable pagelable) { 
		model.addAttribute("boards", boardService.topicList(pagelable));
		return "index"; // return할 때 viewResolver 작동!!
		// viewResolver가 작동하면 해당 index페이지로 이동할 때 model의 정보를 들고 이동하게 된다.
		// index의 앞, 뒤에는 application.yml에서 정의해둔 prefix와 surfix가 자동으로 붙어서 완성된 경로와 파일명을 리턴하여 불러온다
		// data type은 collection
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.topicDetail(id));
		return "board/detail";
		
	}
	
	// USER권한이 필요
	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
}
