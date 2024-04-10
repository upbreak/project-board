가장 기본적이고 보편적인 게시판 기능 개발해본다.
<br/>최신의 스프링부트와 관련된 기술들을 사용하여 만들어 보았다.
<br/>백앤드 기준으로 공부를 하기 위해 만들었기 때문에 화면은 간단하게 만들 수 있는 타임리프를 사용하여 구현하였다.

## 개발 환경
* Intellij IDEA Ultimate 2023.3.2
* Java 17
* Gradle 8.5
* Spring Boot 3.2.1

## 기술 세부 스택
Spring Boot

* Spring Boot Actuator
* Spring Web
* Spring Data JPA
* Rest Repositories
* Rest Repositories HAL Explorer
* Thymeleaf
* Spring Security
* H2 Database
* MySQL Driver
* Lombok
* Spring Boot DevTools
* Spring Configuration Processor
  
그 외
* QueryDSL 5.0.0
* Bootstrap 5.2.0-Beta1
* Heroku

## Feature List
* 도메인 설계
* jpa + querydsl를 이용한 crud 구현
* json api를 자동으로 만들기 위하여 data-rest + hal explorer 환경설정
* Thymeleaf를 이용하여 화면 구현
* spring security를 이용한 인증기능 구현
* 해시태그 검색 기능 추가
* 댓글+대댓글 기능 추가
* kakao API를 이용하여 카카오톡 인증기능 추가

## Reference
* https://app.diagrams.net/#Hupbreak%2Ffastcampus-project-board%2Fmain%2Fdoument%2Fproject-board_erd.svg
