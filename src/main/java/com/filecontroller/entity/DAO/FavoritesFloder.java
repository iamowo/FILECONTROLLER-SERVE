package com.filecontroller.entity.DAO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FavoritesFloder {
    private Integer id;
    private String title;
    private String intro;
    private String coverpath;
    private Integer nums;
    private Timestamp time;
    private Integer type;
}
