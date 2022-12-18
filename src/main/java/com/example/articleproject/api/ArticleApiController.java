package com.example.articleproject.api;

import com.example.articleproject.dto.ArticleForm;
import com.example.articleproject.entity.Article;
import com.example.articleproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //Rest API용 컨트롤러
//데이터를 JSON 형태로 반환
public class ArticleApiController {

    @Autowired // Dependency Injection(외부에서 가져온다)
    private ArticleRepository articleRespoitory;
    // Get
    @GetMapping("/api/articles")
    public List<Article> index()
    {
        return articleRespoitory.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id)
    {
        return articleRespoitory.findById(id).orElse(null);
    }

    // Post
    @PostMapping("/api/articles")
    // 기존 Controller에서는 ArticleForm으로 던지면 알아서 받아줬다.
    // 현재는 @RequestBody라는 annotation을 추가해야된다.
    public Article create(@RequestBody ArticleForm dto)
    {
        Article article=dto.toEntity();
        return articleRespoitory.save(article);
    }

    // Patch
    @PatchMapping("/api/articles/{id}")
    //Response에 Article을 담아서 보낸다.
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto)
    {
        // 1: 수정용 엔티티 생성
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
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id)
    {
        // 대상찾기
        Article target=articleRespoitory.findById(id).orElse(null);
        if(target==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // 대상 삭제
        articleRespoitory.delete(target);
        // 데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
