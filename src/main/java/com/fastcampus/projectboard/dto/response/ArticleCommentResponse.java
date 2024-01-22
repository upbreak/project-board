package com.fastcampus.projectboard.dto.response;

import com.fastcampus.projectboard.dto.ArticleCommentDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record ArticleCommentResponse(
        Long id
        , String content
        , LocalDateTime createAt
        , String email
        , String nickname
        , String userId
        , Long parentCommentId
        , Set<ArticleCommentResponse> childComments
) {
    public static ArticleCommentResponse of(Long id, String content, String userId, LocalDateTime createAt, String email, String nickname) {
        return ArticleCommentResponse.of(id, content, userId, createAt, email, nickname, null);
    }

    public static ArticleCommentResponse of(Long id, String content, String userId, LocalDateTime createAt, String email, String nickname, Long parentCommentId) {
        //화면에 보여줄 때 대댓글을 시간순 오름차순으로 보여줄 것이기 때문에 Comparator를 사용하여 정렬을 하였다.
        Comparator<ArticleCommentResponse> cildCommentComparator = Comparator
                .comparing(ArticleCommentResponse::createAt)//오름차순이 디폴트, 내림차순을 할 경우 .reversed()를 붙이면 된다.
                .thenComparingLong(ArticleCommentResponse::id);
        return new ArticleCommentResponse(id, content, createAt, email, nickname, userId, parentCommentId, new TreeSet<>(cildCommentComparator));
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        String nickname = dto.userAccountDto().nickname();
        if(nickname == null || nickname.isBlank()){
            nickname = dto.userAccountDto().userId();
        }

        return ArticleCommentResponse.of(
                dto.id()
                , dto.content()
                , dto.userAccountDto().userId()
                , dto.createAt()
                , dto.userAccountDto().email()
                , nickname
                , dto.parentCommentId()
        );
    }

    public boolean hasParentCommet(){
        return parentCommentId != null;
    }

}
