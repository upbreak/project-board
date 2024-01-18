package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Hashtag;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fastcampus.projectboard.domain.Hashtag}
 */
public record HashtagDto(
        Long id
        , String hashtagName
        , LocalDateTime createAt
        , String createBy
        , LocalDateTime modifiedAt
        , String modifiedBy
) {
    public static HashtagDto of(String hashtagName) {
        return new HashtagDto(null, hashtagName, null, null, null, null);
    }

    public static HashtagDto from(Hashtag entity){
        return new HashtagDto(
                entity.getId()
                , entity.getHashtagName()
                , entity.getCreateAt()
                , entity.getCreateBy()
                , entity.getModifiedAt()
                , entity.getModifiedBy()
        );
    }

    public Hashtag toEntity(){
        return Hashtag.of(hashtagName);
    }
}