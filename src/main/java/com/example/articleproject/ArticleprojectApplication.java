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



	}

}
