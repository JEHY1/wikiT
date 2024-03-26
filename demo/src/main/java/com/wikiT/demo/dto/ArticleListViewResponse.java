package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListViewResponse {
    private final int SUMMARY_SIZE = 50;

    private Long articleId;
    private String title;
    private String content;

    public ArticleListViewResponse(Article article){
        this.articleId = article.getId();
        this.title = article.getTitle();

        if(article.getContent().length() > SUMMARY_SIZE){
            this.content = article.getContent().substring(0, SUMMARY_SIZE) + "...";
        }
        else{
            this.content = article.getContent();
        }

    }
}
