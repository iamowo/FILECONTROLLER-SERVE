package com.filecontroller.entity;

import lombok.Data;

import java.util.List;

@Data
public class MangaChapter {
    private Integer id;
    // 图片个数
    private Integer imgnum;
    private List<String> imgpath;
    private Long filesize;
}
