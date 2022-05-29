package com.matrix.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrix.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}