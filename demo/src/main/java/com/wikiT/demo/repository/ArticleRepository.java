package com.wikiT.demo.repository;

import com.wikiT.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<List<Article>> findBySpaceAndGroupId(String space, Long groupId);
    Optional<List<Article>> findByGroupId(Long groupId);

}
