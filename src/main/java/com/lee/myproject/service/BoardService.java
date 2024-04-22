package com.lee.myproject.service;

import com.lee.myproject.dto.board.BoardDto;
import com.lee.myproject.entity.Member;
import com.lee.myproject.entity.board.Board;
import com.lee.myproject.repository.BoardRepo;
import com.lee.myproject.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepo boardRepo;
    private final MemberRepo memberRepo;

    public Long save(BoardDto boardDto, Long id) {
        Member member = memberRepo.findById(id).orElse(null);
        if (member == null) {
            return 0L;
        }
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setAuthor(boardDto.getAuthor());
        board.setMember(member);

        Board savedBoard = boardRepo.save(board);
        return savedBoard != null ? 1L : 0;

    }

    public BoardDto getBoardById(Long id) {
        Board board = boardRepo.findById(id).orElse(null);
        return convertDTO(board);
    }

    public List<BoardDto> getAllBoards() {
        List<Board> boardList = boardRepo.findAll();
        return boardList.stream().map(BoardService::convertDTO).collect(Collectors.toList());
    }

    public void updateBoard(BoardDto boardDto, Long id) {
        Board board = boardRepo.findById(id).orElse(null);
        if (board == null) {
//            System.out.println("글이 없음ㅇ");
        }
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setAuthor(boardDto.getAuthor());
        board.setDateTime(boardDto.getDateTime());

        boardRepo.save(board);

    }

    public void deleteBoard(Long id) {
        Board board = boardRepo.findById(id).orElse(null);
        if (board == null) {
            System.out.println("그런 글 없는데?");
        }
        boardRepo.deleteById(id);
    }

    public static BoardDto convertDTO(Board board) {
        if (board == null) {
            return null;
        }
        BoardDto boardDto = new BoardDto();
        boardDto.setAuthor(board.getAuthor());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());

        return boardDto;
    }
}
