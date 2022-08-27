package com.example.articleproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//DB가 인식하기위한 어노테이션
@Entity
@AllArgsConstructor
@ToString
public class Article {
    //Entity에는 기본적으로 대표값이 필요하다.
    //구분짓기위한 값
    @Id //대표값 지정
    @GeneratedValue // 자동생성
    private Long id;
    //테이블이라는 DB의 단위에 연결되게 하는 과정
    @Column
    private String title;
    @Column
    private String content;



}
