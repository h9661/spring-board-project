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

    public void writeBoard(Board board, MultipartFile file) throws Exception {
        // 파일이 존재하면 파일을 저장하고, 파일 이름과 경로를 board 객체에 저장한다.
        if(file != null && !file.isEmpty()) {
            String fileSavingPath = System.getProperty("user.dir") + "/files/";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + "_" + file.getOriginalFilename();

            File dest = new File(fileSavingPath, fileName);
            file.transferTo(dest);

            board.setFilename(fileName);
            System.out.println(fileName);
            board.setFilepath("/files/" + fileName);
        }

        boardRepository.save(board);
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