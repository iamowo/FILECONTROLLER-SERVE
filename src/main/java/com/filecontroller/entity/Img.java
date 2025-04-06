package com.filecontroller.entity;

import lombok.Data;

@Data
public class Img {
    private Integer id;

    private String filename;

    private String path;
    private Integer width;
    private Integer height;
    // 上传时间
    private String time;
    private Integer watchs;

    private Long filesize;
}
