package com.filecontroller.entity.DAO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Resource {
    private Integer id;
    private Timestamp time;
    private String title;
    private String intro;
    private String path;
    private String coverPath;
    private Integer watchs;
}
