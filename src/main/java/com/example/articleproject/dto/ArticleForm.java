package com.example.articleproject.dto;

import com.example.articleproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//폼데이터를 받아올 그릇
@AllArgsConstructor //생성자관련한 롬복
@ToString //ToString관련한 롬복
public class ArticleForm {
    //두 개의 데이터를 던져줄 예정이기에
    //두 개의 변수를 만든다.

    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        //생성자를 호출
        return new Article(id,title,content);
    }
}
