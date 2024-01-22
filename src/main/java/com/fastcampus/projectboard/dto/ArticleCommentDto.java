package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.ArticleComment}
 */
public record ArticleCommentDto(
        Long id
        , Long articleId
        , UserAccountDto userAccountDto
        , Long parentCommentId
        , String content
        , LocalDateTime createAt
        , String createBy
        , LocalDateTime modifiedAt
        , String modifiedBy) {
    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, String content) {
        return ArticleCommentDto.of(articleId, userAccountDto, null, content);
    }

    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content) {
        return new ArticleCommentDto(null, articleId, userAccountDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto from(ArticleComment entity){
        return new ArticleCommentDto(
                entity.getId()
                , entity.getArticle().getId()
                , UserAccountDto.from(entity.getUserAccount())
                , entity.getParentCommentId()
                , entity.getContent()
                , entity.getCreateAt()
                , entity.getCreateBy()
                , entity.getModifiedAt()
                , entity.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article entity, UserAccount userAccount){
        return ArticleComment.of(entity, userAccount, content);
    }
}