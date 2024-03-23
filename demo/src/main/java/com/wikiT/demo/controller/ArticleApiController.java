package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.dto.AddArticleRequest;
import com.wikiT.demo.dto.ArticleStatusViewRequest;
import com.wikiT.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<Article> saveArticle(@RequestBody AddArticleRequest request){

        System.err.println("run /api/article");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleService.save(request));
    }


}
