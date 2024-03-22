package com.wikiT.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleStatusViewRequest {

    private Long articleId;
    private String space;
    private Long groupId;
}
