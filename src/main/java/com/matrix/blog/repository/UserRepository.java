package com.matrix.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matrix.blog.model.User;

// DAO(JSP)
// 자동으로 bean등록이 된다. -> 스프링 IoC에서 객체를 들고 있게 된다
//@Repository // 생략가능하다
public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username); // 네이밍쿼리 규칙.Username 앞 글자는 대문자로 꼭 시작.
}

// User table을 관리하는 Repository
// User table의 primary key는 Integer를 의미
// JpaRepository class의 save() function으로 insert와 update를 할 수 있다
// 기본적인 CRUD는 이렇게만 만들어 두면 얘가 모든 함수를 다 들고 있게된다. 

// JPA Naming 전략
// SELECT * FROM user WHERE username = ? AND password = ?;
// User findByUsernameAndPassword(String username, String password);

// 2가지를 모두 사용할 수 있음
//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//User login(String username, String password);