package com.fastcampus.projectboard.domain.Projection;

import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "withUserAccount", types = ArticleComment.class)
public interface ArticleCommentProjection {
    Long getId();
    UserAccount getUserAccount();
    String getParentCommentId();
    String getContent();
    LocalDateTime getCreateAt();
    String getCreateBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}
