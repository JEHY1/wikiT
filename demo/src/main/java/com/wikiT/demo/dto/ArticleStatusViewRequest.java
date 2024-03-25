package com.wikiT.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleStatusViewRequest {

    private Long articleId;
    private String space;
    private Long groupId;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
