package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListViewResponse {

    private Long articleId;
    private String title;
    private String content;

    public ArticleListViewResponse(Article article){
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
