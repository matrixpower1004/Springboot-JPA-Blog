package com.matrix.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// auto_increment
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 어느 게시글에 누가 작성한 답변인가?
	// 여러개의 답변이 하나의 게시글에 존재할 수 있음 : Many = reply, One = board
	@ManyToOne
	@JoinColumn(name="boardId") // 컬럼명만 정했지 어디와 연관되어 있는지 모른다
	private Board board;
	
	//한 명의 유저는 여러개의 답글을 달 수 있음, 여러개의 답글을 한 명의 유저가 달 수 있음
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
