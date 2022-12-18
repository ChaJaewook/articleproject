package com.example.articleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

//DB가 인식하기위한 어노테이션
@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor //디폴트 생성자를 추가하는 annotation
@Getter
public class Article {
    //Entity에는 기본적으로 대표값이 필요하다.
    //구분짓기위한 값
    @Id //대표값 지정
    //DB가 ID를 자동생성하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    private Long id;
    //테이블이라는 DB의 단위에 연결되게 하는 과정
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title!=null)
            this.title=article.title;
        if(article.content!=null)
            this.content=article.content;
    }

    //lombok대체
    /*public Long getId() {
        return id;
    }*/


    /*Article()
    {

    }*/



}
