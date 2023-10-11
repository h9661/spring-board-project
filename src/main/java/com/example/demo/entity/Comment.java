package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "board_id")
  private Board board;

  // N:1 mapping with User
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Comment() {
    this.createdAt = LocalDateTime.now();
  }
}
