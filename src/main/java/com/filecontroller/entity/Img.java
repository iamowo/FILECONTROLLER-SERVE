package com.filecontroller.entity;

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

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getWatchs() {
        return watchs;
    }

    public void setWatchs(Integer watchs) {
        this.watchs = watchs;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", time='" + time + '\'' +
                ", watchs=" + watchs +
                ", filesize=" + filesize +
                '}';
    }
}
