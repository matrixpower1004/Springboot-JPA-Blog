package com.matrix.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.blog.model.RoleType;
import com.matrix.blog.model.User;
import com.matrix.blog.repository.UserRepository;

// html파일이 아니라 데이터를 리턴해주는 controller = RetController
@RestController
public class DummyControllerTest {

	// userRepository는 스프링이 @ResoController 어노테이션을 읽어서 DummyControllerTest를 메모리에 띄워줄때 Null임.
	// DummyControllerTest가 메모리에 로드될 때 userRepository도 같이 로드된다.
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // 귀찮으면 Exception을 사용해도 되기는 함.
			// 완벽하게 걸려면 EmptyResultDataAccessException을 걸어야 함.
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		// 이렇게만 처리하면 db에 없는 엔티티가 요청될 경우 에러 발생.
		return "삭제되었습니다. id : " + id;
	}
	
	// save함수:
	//		=> insert : id를 전달하지 않거나, 전달한 id에 대한 데이터가 없을 때
	// 		=> update : id를 전달하면 해당 id에 대한 데이터가 있으면 update를 수행
	//@Trancactional 어노테이션을 걸면 save를 하지 않아도 업데이트가 된다.
	@PutMapping("/dummy/user/{id}")
	@Transactional // 함수 종료시에 자동 commit이 됨
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // email, password를 받아오기 위해서 User Object 사용
		//RequestBody : json으로 데이터를 받기위해 필요
		//json데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줘요.)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		// 자바는 파라미터에 함수를 집어넣을 수 없다.
		// 대신 자바 1.8부터 람다식을 넣을 수 있다. 
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		}); // 영속화
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user);
		
		// 더티체킹 :
		//		=> DB에서는 쌓아두었다가 insert, update 등 트랜젝션이 일어나야 하는 일들을 한번에 처리하는데 이를 더티체킹이라고 한다.  	
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll(); // findAll 함수의 리턴 type은 제네릭
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pagelable) {
		Page<User> pagingUser = userRepository.findAll(pagelable);
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 람다식으로 코드를 간결하게
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id : ");
		});	
		return user;
		
		// user/4를 찾을 때 내가 데이터베이스에서 못찾아오게 되면 user값이 null이 될것 아냐?
		// 그럼 return null이 리턴이 되니... 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지판단해서 return해!! 
//		User user = userRepository.findById(id).get(); // return type이 Optional임. 절대 null 값이 리턴될리 없을 때 -> 조금 위험한 방법
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			// interface를 new 하려면 익명 class를 만들어야한다.
//			// 데이터베이스에 없는 쿼리가 들어왔을 때 이 부분이 실행되어 빈 객체를 user에 넣고 return 
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				return new User();
//			}
//		});
		
		// 요청:웹브라우저
		// user 객체 = 자바 오브젝트 
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
		// user object를 json으로 변환해서 브라우저에게 던져줍니다.
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//			}
//		});


	}
	
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고(요청)
	// User id, role, 시간도 자동으로 입력된다.
	// public String join(@RequestParam("username")String u)
	// 원칙은 이런식으로 적지만 변수명을 정확하게 맞추어주기만 해도 된다.
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) { // key=value(약속된 규칙)
	public String join(User user) { // Objcet로 파라메터를 받을 수 있게 해주는 강력한 기능
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER );
		userRepository.save(user); //save()는 DB에 inseret할 때 사용
		return "회원가입이 완료되었습니다.";
	}
}
