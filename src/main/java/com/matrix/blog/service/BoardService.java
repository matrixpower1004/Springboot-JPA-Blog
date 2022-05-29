package com.matrix.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matrix.blog.model.Board;
import com.matrix.blog.model.User;
import com.matrix.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void boardWrite(Board board, User user) { // title, content, user
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> topicList(Pageable pagelable) {
		return boardRepository.findAll(pagelable);
	}
	
	@Transactional(readOnly = true)
	public Board topicDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void deleteTopic(int id) {
//		System.out.println("글삭제하기 : "+id);
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void updateTopic(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패:아이디를 찾을 수 없습니다.");
				}); // 수정하려면 영속화를 먼저 시켜야한다
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭센이 종료됩니다. 이때 더티체킹이 발생 - 자동 업데이트가 됨. DB flush 
	}
	
}
