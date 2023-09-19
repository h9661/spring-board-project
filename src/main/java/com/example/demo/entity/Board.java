package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // 1:n 관계 with Image
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Image> images;
}
