package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository; // 테스트 코드를 구상할 때 스프링부트 테스트를 사용하지 않고 필요한 의존성을 모킹하는 방식으로 접근해서 테스트 하도록 함

    // 검색
    // 각 게시글 페이지로 이동
    // 페이지네이션
    // 홈 버튼 -> 게시판 페이지 리다이렉션
    // 정렬 기능

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글을 조회하면, 게시글 리스트를 반환한다.")
    @Test
    void givenArticleId__whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        ArticleDto articles = sut.searchArticle(1L); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        ArticleDto dto = new ArticleDto(LocalDateTime.now(), "Uno", "title","content","#java");
        given(articleRepository.save(any(Article.class))).willReturn(null); // Mockito 라이브러리 사용
        // When
        sut.saveArticle(dto);
        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다")
    @Test
    void givenArticleIdAndModifyInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        ArticleUpdateDto dto = new ArticleUpdateDto("title","content","#java");
        given(articleRepository.save(any(Article.class))).willReturn(null); // Mockito 라이브러리 사용
        // When
        sut.updateArticle(1L,dto);
        // Then
        then(articleRepository).should().save(any(Article.class));

    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class)); // Mockito 라이브러리 사용
        // When
        sut.deleteArticle(1L);
        // Then
        then(articleRepository).should().delete(any(Article.class));

    }


}