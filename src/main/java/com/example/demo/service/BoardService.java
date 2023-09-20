package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Board getBoard(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board deleteBoard(Integer id){
        Board boardToDelete = boardRepository.findById(id).orElse(null);

        if(boardToDelete == null) {
            return null;
        } else{
            boardRepository.delete(boardToDelete);

            return boardToDelete;
        }
    }
}