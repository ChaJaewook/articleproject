package com.example.articleproject.service;

import com.example.articleproject.dto.CommentDto;
import com.example.articleproject.entity.Article;
import com.example.articleproject.entity.Comment;
import com.example.articleproject.repository.ArticleRepository;
import com.example.articleproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    //Comment 데이터를 가져올때 Article 데이터도 가져와야하기에 필요하다.
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록
//        List<Comment> comments=commentRepository.findByArticleId(articleId);

        // 변환 : 엔티티 -> dto
        /*List<CommentDto> dtos=new ArrayList<CommentDto>();
        for(int i=0;i<comments.size();i++)
        {
            Comment c=comments.get(i);
            CommentDto dto=CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment->CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외처리

        //만약에 없다면 예외처리
        //있으면 실행되고 없으면 예외가 반환된다.
        Article article=articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment=Comment.createComment(dto,article);

        // 댓글 엔티티를 DB로 저장
        Comment created=commentRepository.save(comment);

        // DTO로 변경해 반환
        return CommentDto.createCommentDto(created);

    }

    //Data를 건드리기에 처리필요
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target=commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱싱
        Comment updated=commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target=commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);

        // 삭제댓글을 dto로 변환
        return CommentDto.createCommentDto(target);
    }
}
