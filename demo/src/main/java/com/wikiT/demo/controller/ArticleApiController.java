package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.dto.AddArticleRequest;
import com.wikiT.demo.dto.ArticleStatusViewRequest;
import com.wikiT.demo.dto.DeleteArticleRequest;
import com.wikiT.demo.dto.UpdateArticleRequest;
import com.wikiT.demo.service.ArticleService;
import com.wikiT.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;
    private final GroupService groupService;

    @PostMapping("/api/article")
    public ResponseEntity<Article> saveArticle(@RequestBody AddArticleRequest request, Principal principal){

        if(articleAuthorize(request.getGroupId(), principal)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(articleService.save(request));
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .build();
        }
    }

    @PutMapping("/api/article")
    public ResponseEntity<Article> updateArticle(@RequestBody UpdateArticleRequest request, Principal principal){

        if(articleAuthorize(articleService.findById(request.getArticleId()).getGroupId(), principal)){
            return ResponseEntity.ok()
                    .body(articleService.update(request));
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .build();
        }
    }

    @DeleteMapping("/api/article")
    public ResponseEntity<Void> deleteArticle(@RequestBody DeleteArticleRequest request, Principal principal){

        if(articleAuthorize(articleService.findById(request.getArticleId()).getGroupId(), principal)){
            articleService.delete(request);
            return ResponseEntity.ok()
                    .build();
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED) //202(요청은 받았으나 이를 의도적으로 실행하지 않음)
                    .build();
        }
    }

    private boolean articleAuthorize(Long groupId, Principal principal){
        if(groupService.findByGroupMakerIdAndMember(groupId, principal.getName()) == null){
            return false;
        }
        else{
            return true;
        }
    }
}
