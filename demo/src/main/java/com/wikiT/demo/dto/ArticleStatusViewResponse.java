package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Article;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ArticleStatusViewResponse {

    private String title;
    private String content;
    private Long articleId;
    private Long groupId;
    private String author;
    private String space;


    public ArticleStatusViewResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
        this.articleId = article.getId();
        this.groupId = article.getGroupId();
        this.author = article.getAuthor();
    }

    public ArticleStatusViewResponse(Long groupId, String space, String author){
        this.groupId = groupId;
        this.space = space;
        this.author = author;
    }

}
