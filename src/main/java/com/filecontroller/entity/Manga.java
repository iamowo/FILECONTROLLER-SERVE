package com.filecontroller.entity;

import lombok.Data;

@Data
public class Manga {
    private Integer id;
    private Integer chapters;
    private String filename;
    private Long filesize;
    private String time;
    private MangaChapter mangaChapter;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MangaChapter getMangaChapter() {
        return mangaChapter;
    }

    public void setMangaChapter(MangaChapter mangaChapter) {
        this.mangaChapter = mangaChapter;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + id +
                ", chapters=" + chapters +
                ", filename='" + filename + '\'' +
                ", filesize=" + filesize +
                ", time='" + time + '\'' +
                '}';
    }
}
