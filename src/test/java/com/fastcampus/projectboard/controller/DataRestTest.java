package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("spring data rest통합테스트는 불필요하므로 제외시킴")
@DisplayName("data rest - api테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("api 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticles_thenReturnsArticlesResponse() throws Exception {

        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk()) // ctrl + space - alr + enter -> static생성
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//                .andDo(print()); // ctrl + space - alr + enter -> static생성
    }

    @DisplayName("api 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestArticle_thenReturnsArticleResponse() throws Exception {

        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk()) // ctrl + space - alr + enter -> static생성
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//                .andDo(print()); // ctrl + space - alr + enter -> static생성
    }

    @DisplayName("api 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticleCommentFromArticle_thenReturnsArticleCommentsResponse() throws Exception {

        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk()) // ctrl + space - alr + enter -> static생성
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//                .andDo(print()); // ctrl + space - alr + enter -> static생성
    }

    @DisplayName("api 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticleComments_thenReturnsArticleCommentsResponse() throws Exception {

        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk()) // ctrl + space - alr + enter -> static생성
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//                .andDo(print()); // ctrl + space - alr + enter -> static생성
    }

    @DisplayName("api 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestArticleComment_thenReturnsArticleCommentResponse() throws Exception {

        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk()) // ctrl + space - alr + enter -> static생성
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//                .andDo(print()); // ctrl + space - alr + enter -> static생성
    }
}
