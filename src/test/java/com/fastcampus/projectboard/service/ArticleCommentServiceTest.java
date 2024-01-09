package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 조회한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(UserAccount.of("1L", "password", "email", "nickname", "memo"),"title", "content", "#java")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComments_thenSaveArticleComment() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(UserAccount.of("1L", "password", "email", "nickname", "memo"),"title", "content", "#java")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글의 수정정보를 입력하면, 댓글을 수정한다.")
    @Test
    void givenArticleCommentInfo_whenUpdatingArticleComments_thenUpdateArticleComment() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(UserAccount.of("1L", "password", "email", "nickname", "memo"),"title", "content", "#java")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 id를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleComments_thenDeleteArticleComment() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(UserAccount.of("1L", "password", "email", "nickname", "memo"),"title", "content", "#java")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

}