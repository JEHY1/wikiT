package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.dto.AddArticleRequest;
import com.wikiT.demo.dto.ArticleStatusViewRequest;
import com.wikiT.demo.dto.UpdateArticleRequest;
import com.wikiT.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<Article> saveArticle(@RequestBody AddArticleRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleService.save(request));
    }

    @PutMapping("/api/article")
    public ResponseEntity<Article> updateArticle(@RequestBody UpdateArticleRequest request){

        return ResponseEntity.ok()
                .body(articleService.update(request));
    }


}
