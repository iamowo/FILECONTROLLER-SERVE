package com.filecontroller.entity;

import lombok.Data;

@Data
public class Manga {
    private Integer id;
    private Integer chapters;
    private String filename;
    private Long filesize;
    private String time;
    private MangaChapter mangaChapter;
}
