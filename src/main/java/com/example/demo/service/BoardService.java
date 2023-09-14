package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void writeBoard(Board board) {
        boardRepository.save(board);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Board getBoard(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board updateBoard(Integer id, Board board){
        Board boardToUpdate = boardRepository.findById(id).orElse(null);

        if(boardToUpdate == null) {
            return null;
        } else{
            boardToUpdate.setTitle(board.getTitle());
            boardToUpdate.setContent(board.getContent());

            return boardRepository.save(boardToUpdate);
        }
    }
}