package com.example.demo.service;

import com.example.demo.DTO.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board saveBoard(BoardDTO board) {
        Board newBoard = new Board();
        newBoard.setContent(board.getContent());
        newBoard.setTitle(board.getTitle());

        return boardRepository.save(newBoard);
    }

    public Board updateBoard(BoardDTO board, Integer id) {
        Board newBoard = new Board();
        newBoard.setId(id);
        newBoard.setContent(board.getContent());
        newBoard.setTitle(board.getTitle());

        return boardRepository.save(newBoard);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Board getBoard(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board deleteBoard(Integer id) {
        Board boardToDelete = boardRepository.findById(id).orElse(null);

        if (boardToDelete == null) {
            return null;
        } else {
            boardRepository.delete(boardToDelete);

            return boardToDelete;
        }
    }
}