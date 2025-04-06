package com.filecontroller.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Video {
    private Integer id;
    // 文件名
    private String filename;
    // 路径
    private String path;
    // 时长
    private String duration;
    // base64 封面
    private String cover;
    // 观看次数
    private Integer watchs;
    // 上传时间
    private String time;
    // 文件大小
    private Long filesize;
}
