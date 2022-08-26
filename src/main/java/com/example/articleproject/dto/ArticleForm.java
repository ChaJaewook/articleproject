package com.example.articleproject.dto;

import com.example.articleproject.entity.Article;

//폼데이터를 받아올 그릇
public class ArticleForm {
    //두 개의 데이터를 던져줄 예정이기에
    //두 개의 변수를 만든다.

    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        //생성자를 호출
        return new Article(null,title,content);
    }
}
