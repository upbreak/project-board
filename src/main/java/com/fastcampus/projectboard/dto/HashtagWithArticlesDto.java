package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Hashtag;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record HashtagWithArticlesDto(
        Long id
        , Set<ArticleDto> articles
        , String hashtagName
        , LocalDateTime createAt
        , String createBy
        , LocalDateTime modifiedAt
        , String modifiedBy
) {
    public static HashtagWithArticlesDto of(Set<ArticleDto> articles, String hashtagName) {
        return new HashtagWithArticlesDto(null, articles, hashtagName, null, null, null, null);
    }

    public static HashtagWithArticlesDto from(Hashtag entity){
        return new HashtagWithArticlesDto(
                entity.getId()
                , entity.getArticles().stream()
                        .map(ArticleDto::from)
                        .collect(Collectors.toUnmodifiableSet())
                , entity.getHashtagName()
                , entity.getCreateAt()
                , entity.getCreateBy()
                , entity.getModifiedAt()
                , entity.getModifiedBy()
        );
    }

    public Hashtag toEntity(){ return Hashtag.of(hashtagName);};
}
