package com.matrix.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//어노테이션을 계속해서 덕지덕지 붙이는 것은 좋은 방법이 아니다.	
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성이 된다. 클래스를 테이블화
//@DynamicInsert // Insert할 때 값이 null인 필드는 제외
public class User {
	
	@Id	// Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 오라클을 사용하면 시퀀스를 따라가고, MySQL을 사용하면 오토인크리먼트를 사용한다는 의미
	// use-new-id-generator-mappings: false -> JPA가 사용하는 기본 넘버링 전략을 따라가지 않는다는 의미
	// 		=> false로 지정하면 사용할 전략을 설정해야 한다.
	private int id;	// sequence, auto_increment, 비워두어도 자동 입력
	
	// 중복방지 동작하지 않으면 yml에서 ddl-auto: create로 바꿔서 서버 재가동 후 update로 수정 후 서버 재가동
	@Column(nullable = false, length = 30, unique=true) // unique=중복방지, 오직 하나만 존재		
	private String username;	// 아이디
	
	@Column(nullable = false, length = 100)	// 해쉬 사용 => 비밀번호 암호화
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

// Enum을 사용하면 데이터에 도메인을 만들어 줄 수 있음. 도메인 = 어떤 범위	
//	@ColumnDefault("'user'")	// 따옴표 주의 : 내부 데이터가 스트링인것을 알려줘야 한다.
	// DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;	// Enum을 쓰는게 좋다. => ADMIN, USER : 오타로 인한 에러를 방지
	
	@CreationTimestamp	// 시간이 자동 입력
	private Timestamp createDate;	// 회원가입 시간, 비워놔도 자동 입력
}
// 실행 전 application.yml 체크사항
// ddl-auto: create => create로 되어 있으면 프로젝트 실행 할 때마다 db table을 새로 생성 -> 기존 데이터가 날아간다.
//		-> 한번 실행 후 update로 변경해야 함.
// MySQL 서비스가 윈도웨서 동작하고 있어야 한다.
// show-sql: true => 이 옵션이 없으면 sql문 실행결과를 console창에 출력하지 않는다.
//     properties:
//	hibernate.format_sql: true => 콘솔창에 결과를 출력할 때 한줄로 출력하는데 이 옵션이 있으면 가독성이 좋게 출력된다.
//
//  physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
//		=> @Entity를 만들 때 필드명 그대로 DB에 필드를 넣어준다는 의미