package com.filecontroller.entity.DAO;

import lombok.Data;

@Data
public class Tag {
    private Integer id;
    private String tag;
    private Integer videos;
    private Integer imgs;
    private Integer mangas;
    private Integer musics;
}
