package com.example.articleproject.service;

import com.example.articleproject.dto.ArticleForm;
import com.example.articleproject.entity.Article;
import com.example.articleproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
// 서비스로 선언
// 서비스 객체를 스프링부트에 생성
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article=dto.toEntity();
        // Post Data에 id를 넣으면
        // 생성이 아닌 기존데이터가 수정될 수 있다.
        // 해당 현상을 방지
        if(article.getId()!=null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: 수정용 엔티티 생성
        Article article=dto.toEntity();
        log.info("id: {}, article: {}",id, article.toString());

        // 2: 대상 엔티티를 조회
        Article target=articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target==null|| id!=article.getId())
        {
            // 400번대 잘못된 요청 처리
            log.info("잘못된 요청! id: {}, article: {}",id, article.toString());
            return null;
        }

        // 4: 업데이트 및 정상 응답(200)

        //Patch 요청쪽에서 데이터를
        // id, content만 담고 title을 담지 않았을 경우에 대비한 처리방식
        target.patch(article);
        Article updated=articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target=articleRepository.findById(id).orElse(null);
        if(target==null)
            return null;

        // 대상 삭제
        articleRepository.delete(target);
        // 데이터 반환
        return target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList=dtos.stream()
                .map(dto-> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article-> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결제실패")
        );

        // 결과값 반환
        return articleList;
    }
}
