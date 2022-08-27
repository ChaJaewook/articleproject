package com.example.articleproject.controller;

import com.example.articleproject.dto.ArticleForm;
import com.example.articleproject.entity.Article;
//Entity 관련
import com.example.articleproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {
    // articleRepository 객체를 new를 통해 생성해야하지만
    // Spring Boot가 미리 생성해놓은 객체를 가져다가 자동 연결.
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm()
    {
        return "articles/new";
    }

    //form에서 post로 던지기에 ..
    //form데이터를 dto라는 객체로 받기
    //parameter로 dto를 던져준다.
    @PostMapping("/articles/creates")
    public String createArticle(ArticleForm form)
    {
        //println은 실제로는 서버에 과부하를 줄 수 있어 사용하지 않는다.
        //실제로는 logging기능을 사용한다.

        // System.out.println(form.toString()); ==> logging 대체
        log.info(form.toString());
        //개요

        //1) DTO를 Entitiy로 변환
        Article article=form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //2) DB에 저장(Repository)를 통해 Entity를 DB에 저장
        Article saved=articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }
}
