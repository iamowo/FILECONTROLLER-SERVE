package com.filecontroller.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Video {
    private Integer id;
    // 路径
    private String path;
    // 时长
    private String duration;
    // base64 封面
    private String cover;
    // 观看次数
    private Integer watchs;
    // 归类时间
    private Timestamp time;
}
