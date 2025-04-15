package com.filecontroller.entity;

import lombok.Data;

@Data
public class Music {
    private Integer id;
    private String filename;
    private String path;
    private String time;
    private String duration;
    private String intro;
    private String author;

    private String coverpath;
    private Integer nums;
    private Long filesize;
}
