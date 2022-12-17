package com.example.articleproject.controller;

import com.example.articleproject.dto.ArticleForm;
import com.example.articleproject.entity.Article;
//Entity 관련
import com.example.articleproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    @PostMapping("/articles/create")
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

        //redirect를 한다.
        return "redirect:/articles/"+saved.getId();
    }

    //{id}처리를 통해 id의 값은 변할 수 있다.
    //@PathVariable을 통해 path값의 {id}의 값을 Long id로 받아온다.
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        log.info("id="+id);

        // 1) id를 이용해 데이터 가져오기
        //repository를 통해 가져오기
        Article articleEntity=articleRepository.findById(id).orElse(null);
        //id값을 찾았는데 없으면 null을 반환해라 ==> orElse

        // 2) 가져온 데이터를 모델에 입력
        model.addAttribute("article",articleEntity);

        // 3) 보여줄 페이지를 설정
        return"articles/show";
    }
    @GetMapping("articles")
    public String index(Model model)
    {
        // 1. 모든 article을 가져온다
        // List<Article> articleEntityList=articleRepository.findAll()
        // 위와 같은 문구를 사용하면 오류가 난다.
        // 해결방법으로는

        //1) 캐스팅
        //List<Article> articleEntityList=(List<Article>)articleRepository.findAll();
        //2) 원하는 타입으로 바꿔준다.
        //Iterable<Article> articleEntityList =articleRepository.findAll();
        //3) 메소드오버라이딩 : ArrayList로 바꿔준다.
        List<Article>articleEntityList=(List<Article>)articleRepository.findAll();


        // 2. 가져온 article 묶음을 뷰로 전달!
        model.addAttribute("articleList",articleEntityList);

        // 3. 뷰 페이지를 설정
        return"articles/index"; //articles.index.mustache
    }
    // {id} 를 써서 변수화 한다.
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model)
    {
        // 수정할 데이터를 가져오기
        Article articleEntity=articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("article",articleEntity);

        // 뷰 페이지 수정
        return "articles/edit";
    }
    // 13. 링크와 리다이렉트
    // Link사용시 보다 편리한 요청이
    // Redirect사용시 보다 편리한 응답이 가능
    // Link란 미리 정해진 요청을 보낸다. HTML의 <FORM>, <a>태그 사용
    // Redirect란 Client에게 재요청을 보낸다.
    // 재요청을 받은 클라이언트는 server에게 다시 요청을 보내 결과를 받는다.


}
