package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddArticleRequest {

    private String title;
    private String content;
    private Long groupId;
    private String author;
    private String space;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .groupId(groupId)
                .author(author)
                .space(space)
                .build();
    }
}
