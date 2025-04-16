package com.filecontroller.serve;

import com.filecontroller.entity.*;
import com.filecontroller.entity.UP.OneCategorize;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public interface FileService {
    List<Video> getUncategorizedVideo(Integer page, Integer num) throws Exception;
    List<Img> getUncategorizedImg(Integer page, Integer num) throws Exception;
    List<Manga> getUncategorizedManga(Integer page, Integer num) throws Exception;
    List<Music> getUncategorizedMusic(Integer page, Integer num) throws Exception;
    Integer getLength(String type);

    void cateorizeFiles(OneCategorize oneCategorize);

    MangaChapter getOneMangaChapter(Integer id, String filepath) throws Exception;
}
