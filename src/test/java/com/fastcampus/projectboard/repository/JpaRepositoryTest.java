package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("testdb")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {


    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository
            , @Autowired ArticleCommentRepository articleCommentRepository
            , @Autowired UserAccountRepository userAccountRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository =  userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void  givenTestData_whenSelecting_thenWorksFine(){


        List<Article> articles = articleRepository.findAll();

        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("insert 테스트")
    @Test
    void  givenTestData_whenInserting_thenWorksFine(){
        long previousCount = articleRepository.count();
//        Article article = Article.of("new article", "new content", "#spring");
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("jinwoo", "pw", null, null, null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");

        articleRepository.save(article);

        assertThat(articleRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void  givenTestData_whenUpdating_thenWorksFine(){

        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashTag = "#springboot";
        article.setHashtag(updatedHashTag);

        Article savedArticle = articleRepository.saveAndFlush(article);

        assertThat(savedArticle)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashTag);
    }

    @DisplayName("delete 테스트")
    @Test
    void  givenTestData_whenDeleting_thenWorksFine(){

        Article article = articleRepository.findById(1L).orElseThrow();
        long preCount = articleRepository.count();
        long preCommentCount = articleCommentRepository.count();
        long deletedCommentSize = article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(preCount -1);
        assertThat(articleCommentRepository.count()).isEqualTo(preCommentCount - deletedCommentSize);
    }
}