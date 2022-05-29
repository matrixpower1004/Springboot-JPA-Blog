package com.matrix.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.blog.config.auth.PrincipalDetail;
import com.matrix.blog.dto.ResponseDto;
import com.matrix.blog.model.Board;
import com.matrix.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService; 
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.boardWrite(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 1을 리턴하면 정상
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
//		System.out.println("글삭제요청 : " + id);
		boardService.deleteTopic(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		// ajax통신을 통한 요청의 파라미터를 받을대는 @RequestBody 어노테이션이 필요
		boardService.updateTopic(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
