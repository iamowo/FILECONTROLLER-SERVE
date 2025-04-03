package com.filecontroller.entity;

import lombok.Data;

import java.util.List;

@Data
public class MangaChapter {
    private Integer id;
    private Integer imgnum;
    private List<String> imgpath;
    private Long filesize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImgnum() {
        return imgnum;
    }

    public void setImgnum(Integer imgnum) {
        this.imgnum = imgnum;
    }

    public List<String> getImgpath() {
        return imgpath;
    }

    public void setImgpath(List<String> imgpath) {
        this.imgpath = imgpath;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "MangaChapter{" +
                "id=" + id +
                ", imgnum=" + imgnum +
                ", imgpath=" + imgpath +
                ", filesize=" + filesize +
                '}';
    }
}
