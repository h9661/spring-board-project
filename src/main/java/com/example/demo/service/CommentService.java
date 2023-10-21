package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {
  @Autowired
  CommentRepository commentRepository;

  public void deleteComment(Integer id) {
    commentRepository.deleteById(id);
  }

  public Comment getComment(Integer id) {
    return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
  }

  public Comment updateComment(Integer id, CommentDTO commentDTO) {
    Comment comment = getComment(id);
    comment.setContent(commentDTO.getContent());
    comment.setUpdatedAt(LocalDateTime.now());
    return commentRepository.save(comment);
  }
}
