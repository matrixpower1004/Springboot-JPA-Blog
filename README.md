# Springboot-JPA-Blog History

### 강의 동영상 링크

[Springboot - 나만의 블로그 만들기](https://www.youtube.com/playlist?list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm)

## 2022-05-23

1. sts4 설치 후 springboot에 MySQL 설정 중 에러 발생
    - 원인 : dependency 설정 누락
    - 해결 : 강의 동영상의 댓글에 해결책 나와 있었음. pom.xml에 아래의 dependency 추가
    
    ```bash
    <dependency>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>2.4.3</version>
    </dependency>
    
    ```
    

1. git push 에서 Permission 에러 발생
    - 원인 : 예전에 테스트로 만들어 본 Github 글로벌 계정의 자격증명이 내 PC에 남아 있어서 발생한 문제
    - 해결 : 아래 블로그 참조하여 사용자 계정의 자격 증명 삭제
    
    [https://recoveryman.tistory.com/392](https://recoveryman.tistory.com/392)
    

### 2022-05-24

1. 브라우저에서 get방식 요청시 로그인 메시지 뜨면서 응답을 하지 않음. 서버 가동시 어제와는 다른 Using default security password : 메시지 출력
    - 원인 : Sringboot security 설정 문제
    - 해결 : springboot에 대한 이해부족으로 근본적인 해결은 하지 못함. okky에 관련 Q&A가 있어서 참고 하였음.
    - POSTMAN에서 Cookies 삭제 후  Authorization에서 Basic Auth → Username : user / Password : 스프링 부트에서 콘솔로 뿌려주는 Using generated security password: 의 코드값을 설정

[OKKY | 스프링 부트로 프로젝트를 생성했는데요 Using default security password:](https://okky.kr/article/333904)

### 2022-05-26

1. User data를 DB에 insert 할 때 중복데이터가 삽입되는 원인 파악 및 수정.
    - @Column(nullable = false, length = 30, unique=true)로 어노테이션 수정
    - application.yml 설정에서 ddl-auto: create로 수정 후 서버재시작 → 다시 update로 수정 후 서버재시작
2. 회원가입 화면까지 생성 완료

### 2022-05-27

1. Ajax를 이용한 전통적인 방식으로 회원가입 및 로그인 기능 구현.
2. 강의에 있는 스프링 시큐리티를 적용하여 수정 예정.
3. Spring JPA를 제대로 적용하려면 기본적인 CS지식이 따라줘야 한다는 것을 절감함.
