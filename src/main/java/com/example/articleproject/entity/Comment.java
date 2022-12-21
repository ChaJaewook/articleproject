package com.example.articleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//여러개의 코멘트가 하나의 Article에 달린다.
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //댓글의 부모게시글
    @ManyToOne
    //이런 관계를 설정해 준다.
    //다대일 관계 설정
    @JoinColumn(name="article_id")
    //테이블에 연결될 대상 저오

    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;
}
