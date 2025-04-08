package com.filecontroller.entity.UP;

import lombok.Data;

import java.util.List;

@Data
public class OneCategorize {
    private String title;
    private String intro;
    private List<String> tags;
    // 收藏夹id
    private Integer ffid;
    private Integer type;
    // 分类之前的地址
    private String ucpath;
    // base64格式的封面
    private String cover;
}
