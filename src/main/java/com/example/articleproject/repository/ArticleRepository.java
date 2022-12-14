package com.example.articleproject.repository;

import com.example.articleproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//JPA에서 제공해주는 Repository를 구현
//CrudRepository에는 <>에 두개의 파라미터가 필요하다.
//첫 번째는 관리대상Entity를 넣어주고, 두 번째로는 Article의 대표값 타입을 넣어준다.
public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();
}
