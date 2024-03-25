package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.dto.ArticleStatusViewRequest;
import com.wikiT.demo.dto.ArticleStatusViewResponse;
import com.wikiT.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {

    private final ArticleService articleService;

    @GetMapping("/newArticle")
    public String showNewArticleView(ArticleStatusViewRequest request, Principal principal, Model model){

        System.err.println("space : " + request.getSpace());

        if(request.getArticleId() == null){
            System.err.println("articleId : " + request.getArticleId());
            model.addAttribute("article", new ArticleStatusViewResponse(request.getGroupId(), request.getSpace(), principal.getName()));
            return "newArticle";
        }

        Article article = articleService.findById(request.getArticleId());
        model.addAttribute("article", new ArticleStatusViewResponse(article));
        model.addAttribute("myEmail", principal.getName());

        return "newArticle";
    }
}
