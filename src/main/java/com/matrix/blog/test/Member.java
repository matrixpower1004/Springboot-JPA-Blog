package com.matrix.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Getter
// @Setter 
// @Data -> Getter, Setter 동시에 쓰고 싶을 때 
// @AllArgsConstructor : 모든 필드를 다 쓰고 싶은 생성자를 만들고 싶을 때
// @RequiredArgsConstructor : final로 선언된 필드들에 대해서만 constructor 생성
// @NoArgsConstructor : 빈생성자
@Data
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	//@Builder 어노테이션은 하나의 생성자만으로도 생성자 오버로딩을 가능하게 해준다.
	//객체 생성시 값을 넣을 때 순서를 지키지 않아도 된다.
	//객체 값 순서를 헷갈려서 잘못 입력하는 실수를 방지해준다.
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	// 생성자(constructor) 만들기
	// 마우스 우클릭 -> source -> Geneterate Constructor using Fields
	// Getter, Setter lombok에서 관리하기 위하여 삭제
	// private로 설정한 필드값을 수정할 수 있게 getter, setter를 만든다.
	// 마우스 우클릭 -> source -> Generate getters and setters 
}
