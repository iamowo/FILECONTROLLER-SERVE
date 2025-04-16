package com.filecontroller.entity;

import lombok.Data;

import java.util.List;

@Data
public class Manga {
    private Integer id;
    // 章节个数
    private Integer chapters;
    private String filename;
    private Long filesize;
    private String time;
    private String path;
    // 一个章节
    private List<MangaChapter> mangaChapter;
    private String coverpath;
}
