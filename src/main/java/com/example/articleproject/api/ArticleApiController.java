package com.example.articleproject.api;

import com.example.articleproject.dto.ArticleForm;
import com.example.articleproject.entity.Article;
import com.example.articleproject.repository.ArticleRepository;
import com.example.articleproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //Rest API용 컨트롤러
//데이터를 JSON 형태로 반환
public class ArticleApiController {
    @Autowired //DI 생성객체를 가져와 연결
    private ArticleService articleService;


    // 서비스 생성을 위한 전체 주석처리
//
//    @Autowired // Dependency Injection(외부에서 가져온다)
//    private ArticleRepository articleRespoitory;
    // Get
    @GetMapping("/api/articles")
    public List<Article> index()
    {
        //return articleRespoitory.findAll();
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id)
    {
        //return articleRespoitory.findById(id).orElse(null);
        return articleService.show(id);
    }
//
//    // Post
    @PostMapping("/api/articles")
    // 기존 Controller에서는 ArticleForm으로 던지면 알아서 받아줬다.
    // 현재는 @RequestBody라는 annotation을 추가해야된다.
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto)
    {
        //Article article=dto.toEntity();
        //return articleRespoitory.save(article);

        Article created=articleService.create(dto);
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
    // Patch
    @PatchMapping("/api/articles/{id}")
    //Response에 Article을 담아서 보낸다.
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto)
    {
        Article updated=articleService.update(id, dto);
        return (updated!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        /*// 1: 수정용 엔티티 생성
        Article article=dto.toEntity();
        log.info("id: {}, article: {}",id, article.toString());

        // 2: 대상 엔티티를 조회
        Article target=articleRespoitory.findById(id).orElse(null);

        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target==null|| id!=article.getId())
        {
            // 400번대 잘못된 요청 처리
            log.info("잘못된 요청! id: {}, article: {}",id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4: 업데이트 및 정상 응답(200)

        //Patch 요청쪽에서 데이터를
        // id, content만 담고 title을 담지 않았을 경우에 대비한 처리방식
        target.patch(article);
        Article updated=articleRespoitory.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);*/
    }
    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id)
    {
        // 대상찾기
        /*Article target=articleRespoitory.findById(id).orElse(null);
        if(target==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // 대상 삭제
        articleRespoitory.delete(target);
        // 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();*/
        Article deleted=articleService.delete(id);
        return (deleted!=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos)
    {
        List<Article> createdList=articleService.createArticles(dtos);

        return (createdList!=null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    /*
//    20. 서비스 계층과 트랜잭션
//     - 서비스 계층이란 컨트롤과 repository사이에 위치하는 계층
//     - 처리업무의 순서를 총괄
//     - 트랜젝션 : 업무처리는 트랜젝션 단위로 일련의 과정
//     */

    // 트랜잭션 -> 실패 -> 롤백!

    /*
    29. IoC Container
    Controller, Service, Repository를 만든다.
    Inversion of Control : 프로그램 흐름이 개발자 코드가 아닌
    IoC Container에의해 통제
    객체간 상호결합을 낮춰 유연하고 객체지향적인 코드 제공
     */

    /*
    30. AOP
    - Loggin, Secure, Transac
    - 핵심 기능과 별개의 기능이 덕지덕지 붙게된다.
    - 부가기능을 특정지점에 잘라 넣어 특정 로직을 주입
    - 간결하고 효율적은 프로그램이 가능하다.

     */

    /*
    31. ObjectMapper
    - JSON과 JAVA 객체사이의 변화을 도와준다.
    - JSON을 자바 객체로(readValue)
    - 자바 객체를 JSON으로 변환(writeValueAsString)

     */
}
