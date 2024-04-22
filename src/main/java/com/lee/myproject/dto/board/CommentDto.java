package com.lee.myproject.dto.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private String content;
    private String author;
    private LocalDateTime dateTime;


}
