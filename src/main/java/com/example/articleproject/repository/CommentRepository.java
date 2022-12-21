package com.example.articleproject.repository;

import com.example.articleproject.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
    // 특정 게시글의 모든 댓글 조회
    // nativeQuery를 true로 줘야 해당 쿼리를 직접 실행한다.
    @Query(value = "SELECT * FROM comment WHERE article_id=:articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
