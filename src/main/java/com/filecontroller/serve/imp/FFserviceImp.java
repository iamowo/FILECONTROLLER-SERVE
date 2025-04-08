package com.filecontroller.serve.imp;

import com.filecontroller.entity.DAO.FavoritesFloder;
import com.filecontroller.mapper.FFMapper;
import com.filecontroller.serve.FFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.filecontroller.utils.DealFiles.createFolder;
import static java.rmi.server.LogStream.log;

@Slf4j
@Service
public class FFserviceImp implements FFService {
    @Autowired
    private FFMapper ffMapper;

    @Value("${files.local.categorized}")
    private String categorizedPath;
    @Override
    public List<FavoritesFloder> getOneType(String type) {
        return ffMapper.getOneType(type);
    }

    @Override
    public void addOneFolder(FavoritesFloder favoritesFloder) {
        String t = categorizedPath;
        if (favoritesFloder.getType() == 0) {
            t += "/video/folders/";
        } else if (favoritesFloder.getType() == 1) {
            t += "/img/folders/";
        } else if (favoritesFloder.getType() == 2) {
            t += "/manga/folders/";
        } else if (favoritesFloder.getType() == 3) {
            t += "/music/folders/";
        }
        t += favoritesFloder.getTitle();
        if (createFolder(t)) {
            log("分类文件创建成功: " + t);
            ffMapper.addOneFolder(favoritesFloder);
        }
    }

    @Override
    public void updateOneFolder(FavoritesFloder favoritesFloder) {
        ffMapper.updateOneFolder(favoritesFloder);
    }
}
