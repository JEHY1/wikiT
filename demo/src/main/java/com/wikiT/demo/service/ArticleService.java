package com.wikiT.demo.service;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.dto.AddArticleRequest;
import com.wikiT.demo.dto.UpdateArticleRequest;
import com.wikiT.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(AddArticleRequest request){
        return articleRepository.save(request.toEntity());
    }

    public Article findById(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found articleId"));
    }

    public List<Article> findBySpaceAndGroupId(String space, Long groupId){
        return articleRepository.findBySpaceAndGroupId(space, groupId)
                .orElseThrow(() -> new IllegalArgumentException("not found Article"));
    }

    @Transactional
    public Article update(UpdateArticleRequest request){
        Article article = articleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("not found Article"));

        article.update(request.getTitle(), request.getContent());
        return article;
    }

}
