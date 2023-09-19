package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;
    private String filepath;

    // n:1 관계 with Board
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
