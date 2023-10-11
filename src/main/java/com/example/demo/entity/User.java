package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;

  @Column(unique = true, length = 45)
  private String username;

  private String password;

  @Column(unique = true, length = 45)
  private String email;

  // 1:N mapping with Board
  @OneToMany(mappedBy = "user")
  private List<Board> boards;

  // 1:N mapping with Comment
  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

}
