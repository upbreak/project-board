package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>
        , QuerydslPredicateExecutor<Article>
        , QuerydslBinderCustomizer<QArticle> {
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true); //QuerydslPredicateExecutor에 의해서 모든 필드의 검색이 열려 있어서 닫아준다
        bindings.including(root.title, root.content, root.hashtag, root.createAt, root.createBy); //원하는 검색 필드를 설정
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '{value}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(root.createAt).first(DateTimeExpression::eq); // like '%{value}%'
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase); // like '%{value}%'
    }
}