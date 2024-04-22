package com.lee.myproject.dto.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lee.myproject.entity.board.Board;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {

    private String title;
    private String content;
    private String author;
    private LocalDateTime dateTime;

    private List<CommentDto> commentDtoList;


    public static BoardDto form (Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setAuthor(board.getAuthor());
        boardDto.setDateTime(board.getDateTime());
        return boardDto;
    }
}
