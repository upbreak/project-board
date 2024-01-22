package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.config.TestSecurityConfig;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.service.ArticleCommentService;
import com.fastcampus.projectboard.util.FormDataEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("view 컨트롤러-댓글")
@Import({TestSecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(ArticleCommentController.class)
class ArticleCommentControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean
    ArticleCommentService articleCommentService;

    public ArticleCommentControllerTest(
            @Autowired MockMvc mvc
            , @Autowired FormDataEncoder formDataEncoder
    ) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("[view][post] 새 댓글 등록 - 정상 호출")
    @WithMockUser
    @Test
    void givenAticleCommentInfo_whenRequesing_thenSaveNewArticleComment() throws Exception {
        // Given
        Long articleId = 1L;
        ArticleCommentRequest request = ArticleCommentRequest.of(articleId, "new comment");
        willDoNothing().given(articleCommentService).saveArticleComment(any(ArticleCommentDto.class));

        // When & Then
        mvc.perform(
                        post("/comments/new")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(request))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/"+articleId))
                .andExpect(redirectedUrl("/articles/"+articleId));
        then(articleCommentService).should().saveArticleComment(any(ArticleCommentDto.class));
    }

    @DisplayName("[view][post] 댓글 삭제 - 정상 호출")
    @WithUserDetails(value = "jinwooTest", userDetailsServiceBeanName = "userDetailsService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void givenArticleIdToDelete_whenRequesting_thenDeletesArticle() throws Exception {
        // Given
        Long articleId = 1L;
        Long commentId = 1L;
        String userId = "jinwooTest";
        willDoNothing().given(articleCommentService).deleteArticleComment(commentId, userId);

        // When & Then
        mvc.perform(
                        post("/comments/"+articleId+"/"+commentId+"/delete")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/" + articleId))
                .andExpect(redirectedUrl("/articles/" + articleId));
        then(articleCommentService).should().deleteArticleComment(articleId, userId);
    }

    @DisplayName("[view][post] 대댓글 등록 - 정상 호출")
    @WithUserDetails(value = "jinwooTest", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void givenArticleCommentInfoWithParentCommentId_whenRequesting_thenSavesNewChildComment() throws Exception {
        // Given
        long articleId = 1L;
        ArticleCommentRequest request = ArticleCommentRequest.of(articleId, 1L, "test comment");
        willDoNothing().given(articleCommentService).saveArticleComment(any(ArticleCommentDto.class));

        // When & Then
        mvc.perform(
                post("/comments/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formDataEncoder.encode(request))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/articles/" + articleId))
                .andExpect(redirectedUrl("/articles/" + articleId));
        then(articleCommentService).should().saveArticleComment(any(ArticleCommentDto.class));
    }
}
