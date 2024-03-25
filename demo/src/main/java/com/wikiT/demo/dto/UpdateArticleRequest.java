package com.wikiT.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArticleRequest {

    private Long articleId;
    private String title;
    private String content;
}
