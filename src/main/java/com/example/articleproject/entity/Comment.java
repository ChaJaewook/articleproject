package com.example.articleproject.entity;

import com.example.articleproject.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외처리
        if(dto.getId()!=null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 없어야합니다.");
        if(dto.getArticleId()!= article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생
        if(this.id!=dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        //객체를 갱신
        if(dto.getNickname()!=null)
            this.nickname=dto.getNickname();
        if(dto.getBody()!=null)
            this.body=dto.getBody();

    }
}
