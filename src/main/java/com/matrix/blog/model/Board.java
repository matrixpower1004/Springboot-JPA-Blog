package com.matrix.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// 이 어노테이션이 클래스 가까이에 있는게 좋다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	// 대용량 데이터
	private String content;	// 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨. 데이터 용량이 상당히 커짐
	
	private int count;	// 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many=Board, User=One (한 명의 유저는 여러개의 게시글을 쓸 수 있다)
//	@OneToOne // 한 명의 유저는 하나의 게시글만 쓸 수 있다. Many=one, User=one
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
						// ORM을 사용하면 object를 그대로 사용할 수 있다.
	
	//@OneToMany는 기본전략이 FetchType.EAGER이 아님.
	//보드테이블을 select할 때 user는 한 명이지만 reply는 엄청나게 많은 수가 존재할 수 있음.  
//	@JoinColumn(name="replyId") => FK가 아니기 때문에 이 어노테이션은 쓰면 안된다. 테이블에 생성되어야 하는 FK가 아니다.
	// 		=> Reply class에 boardId가 FK로 이미 존재하고 있음
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 컬럼을 만들지 마세요.
	private List<Reply> reply; // reply은 한 건이 아니라 여러 건임 
	
	@CreationTimestamp	// 데이터가 인서트되거나 업데이트 될 때 자동으로 시간이 들어감
	private Timestamp createDate;
}
