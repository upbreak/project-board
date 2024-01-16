package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.dto.request.ArticleCommentRequest;
import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import com.fastcampus.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String saveNewArticleComment(
            ArticleCommentRequest articleCommentRequest
            , @AuthenticationPrincipal BoardPrincipal BoardPrincipal
    ){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(BoardPrincipal.toDto()));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{articleId}/{articleCommentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long articleCommentId
            , @PathVariable Long articleId
            , @AuthenticationPrincipal BoardPrincipal boardPrincipal
            ){
        articleCommentService.deleteArticleComment(articleCommentId, boardPrincipal.username());

        return "redirect:/articles/" + articleId;
    }
}
