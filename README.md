# Springboot-JPA-Blog History

## Springboot의 JPA와 Security를 적용한 블로그 만들기 프로젝트

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
    
2. git push 에서 Permission 에러 발생
    - 원인 : 예전에 테스트로 만들어 본 Github 글로벌 계정의 자격증명이 내 PC에 남아 있어서 발생한 문제
    - 해결 : 아래 블로그 참조하여 사용자 계정의 자격 증명 삭제
    
    [https://recoveryman.tistory.com/392](https://recoveryman.tistory.com/392)
    

### 2022-05-24
1. 브라우저에서 get방식 요청시 로그인 메시지 뜨면서 응답을 하지 않음. 서버 가동시 어제와는 다른 Using default security password : 메시지 출력
    - 원인 : Sringboot security 설정 문제
    - 해결 : Spring security에 대한 이해부족으로 근본적인 해결은 하지 못함. okky에 관련 Q&A가 있어서 참고 하였음
    - POSTMAN에서 Cookies 삭제 후  Authorization에서 Basic Auth → Username : user / Password : 스프링 부트에서 콘솔로 뿌려주는 Using generated security password: 의 코드값을 설정

[OKKY | 스프링 부트로 프로젝트를 생성했는데요 Using default security password:](https://okky.kr/article/333904)

### 2022-05-26
1. User data를 DB에 insert 할 때 중복데이터가 삽입되는 원인 파악 및 수정
    - @Column(nullable = false, length = 30, unique=true)로 어노테이션 수정
    - application.yml 설정에서 ddl-auto: create로 수정 후 서버 재시작하여 DB 초기화 → 다시 update로 수정 후 서버 재시작
2. 회원가입 화면까지 완료

### 2022-05-27
1. Ajax를 이용한 전통적인 방식으로 회원가입 및 로그인 기능 테스트
2. Spring JPA를 제대로 적용하려면 SQL에 대한 지식뿐만 아니라 DB domian 지식과 작동원리에 대한 이해가 필요하다는 것을 실감함
3. 정보처리 기사 필기 시험을 위해 짧게나마 공부한 것이 많은 도움이 되고 있음.
4. 실기시험 합격과 내 성장을 위해서라도 정보처리기사 자격증은 꼭 취득해야겠다는 다짐을 다시 한번...

### 2022-05-28
1. Spring security를 적용하여 회원 로그인 및 로그아웃 기능 구현
2. 처음 접하는 프레임워크인데다 최근 업데이트로 인해서 강의 영상과는 다른 부분들이 조금 있어서 적용이나 이해에 어려움이 있음
3. security를 비즈니스 프로젝트에 적용하기에는 현재의 지식만으로는 부족하다는 것을 느낌

### 2022-05-29
1. Spring security를 이해하려면 시간이 필요할 듯
2. 단기적으로 툴이나 프레임워크 사용법을 익히는 것이 중요하지만, 장기적으로는 기초 지식이 더 중요하다는 것을 계속 체감하고 있음
3. js에서 HTML DOM 객체에 접근하는 다양한 방법들을 더 공부할 필요성을 느낌

