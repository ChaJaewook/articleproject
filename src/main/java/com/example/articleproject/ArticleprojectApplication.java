package com.example.articleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticleprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleprojectApplication.class, args);
		// 파일명을 직접 언급시 resources/static 폴더 내부에서 찾는다.
		// resources/static/hello.html 파일을 경로에 넣어주면 실행된다.
		// MVC 패턴 View Template : 화면 담당, Controller:처리과정 담당, Model:데이터 담당
		// 각 담당자를 나누는걸 MVC패턴이라고 한다.

		// Layout : 화면배치
		// Header(사이트 안내), Footer(사이트 정보)

		// CRUD

		// 폼데이터 : HTML 요소인 FORM태그에 담긴 데이터 요소 <form>(어디로, 어떻게 보낼지를 기재)
		// 해당 데이터를 Controller 객체가 받고 그객체를 DTO라고 한다.

		// View Page의 어디로 보낼지 action과 어떻게 보낼지 method로 구분
		// dto객체에 담기게한다.

		// DB는 JAVA를 모른다. DB는 SQL을 사용한다.
		// 어떻게 JAVA를 DB가 알아들을 수 있게 해주는게 JPA, 데이터 관리에 필요한 기능도 제공
		// Entity(DB가 이해 할 수 있는 규격화된 데이터)와 Repository(통해서 DB에 전달)가 있다.

		// DTO를 Entitiy로 변환하고 Repository를 통해 DB에 저장




	}

}
