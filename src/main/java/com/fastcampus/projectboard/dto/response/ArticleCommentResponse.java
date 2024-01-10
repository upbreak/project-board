package com.fastcampus.projectboard.dto.response;

import com.fastcampus.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;

public record ArticleCommentResponse(
        Long id
        , String content
        , LocalDateTime createAt
        , String email
        , String nickname
) {
    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createAt, String email, String nickname) {
        return new ArticleCommentResponse(id, content, createAt, email, nickname);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        String nickname = dto.userAccountDto().nickname();
        if(nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        return new ArticleCommentResponse(
                dto.id()
                , dto.content()
                , dto.createAt()
                , dto.userAccountDto().email()
                , nickname
        );
    }
}
