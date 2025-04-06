package com.filecontroller.entity;

import lombok.Data;

import java.util.List;

@Data
public class MangaChapter {
    private Integer id;
    private Integer imgnum;
    private List<String> imgpath;
    private Long filesize;
}
