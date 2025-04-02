package com.filecontroller.entity;

import lombok.Data;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getWatchs() {
        return watchs;
    }

    public void setWatchs(Integer watchs) {
        this.watchs = watchs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", duration='" + duration + '\'' +
                ", cover='" + cover + '\'' +
                ", watchs=" + watchs +
                ", time='" + time + '\'' +
                ", filesize=" + filesize +
                '}';
    }
}
