package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String saveNewArticleComment(ArticleCommentRequest articleCommentRequest){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(
                //TODO: 인증정보 넣어야 함.
                UserAccountDto.of("1", "asdf1234", "Jinwoo", "upbreak@email.com", "Im jinwoo", null, null, null, null)
        ));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{articleId}/{articleCommentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long articleCommentId
            , @PathVariable Long articleId
    ){
        articleCommentService.deleteArticleComment(articleCommentId);

        return "redirect:/articles/" + articleId;
    }
}
