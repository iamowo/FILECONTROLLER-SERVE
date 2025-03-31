package com.filecontroller.serve.imp;

import com.filecontroller.entity.Video;
import com.filecontroller.serve.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.filecontroller.utils.DealFiles.*;

@Service
public class FileServiceImp implements FileService {

    @Value("${files.unCategorized}")
    private String unCategorizedPath;

    private String[] imgsuffixs ={"jgp", "JPG", "gif", "GIF", "png", "PNG", "webp", "WEBP"};
    private String[] videosuffixs = {"mp4", "MP4"};
    private String[] musicssuffixs = {"mp3", "MP3"};

    @Override
    public Map<String, Object> getAllUncategorized() throws Exception {
        Map<String, Object> res = new HashMap<>();

        File file = new File(unCategorizedPath);
        List<String> videopath = new ArrayList<>();
        List<String> imgpath = new ArrayList<>();
        List<String> mangapath = new ArrayList<>();
        List<String> musicpath = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    // manga
                    mangapath.add(files[i].getPath());
                } else {
                    String filename = files[i].getPath();
                    // .jpg
                    // String suffix = filename.substring(filename.lastIndexOf("."));
                    // jpg
                    String suffix=filename.split("\\.")[filename.split("\\.").length-1];
                    if (Arrays.asList(imgsuffixs).contains(suffix)) {
                        Video video = new Video();
                        // base 64 封面
                        String firstFrameBase64 = getFirstFrameAsBase64(filename);
                        // 时长
                        String duration = getVideoDuration(filename);
                        // 上传日期
                        long uploadTimestamp = getUploadTimestamp(filename);
                        String upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(uploadTimestamp));

                        imgpath.add(filename);
                    } else if (Arrays.asList(videosuffixs).contains(suffix)) {
                        videopath.add((filename));
                    } else if (Arrays.asList(musicssuffixs).contains(suffix)) {
                        musicpath.add((filename));
                    }
                }
            }
        }
        res.put("mangas", mangapath);
        res.put("videos", videopath);
        res.put("musics", musicpath);
        res.put("imgs", imgpath);
        return res;
    }
}
