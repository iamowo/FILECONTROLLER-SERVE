package com.filecontroller.serve.imp;

import com.filecontroller.entity.Img;
import com.filecontroller.entity.Manga;
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

    @Value("${files.local.categorized}")
    private String categorizedPathLocal;
    @Value("${files.local.unCategorized}")
    private String unCategorizedPathLocal;
    @Value("${files.net.categorized}")
    private String categorizedPathNet;
    @Value("${files.net.unCategorized}")
    private String unCategorizedPathNet;

    private String[] imgsuffixs ={"jgp", "JPG", "gif", "GIF", "png", "PNG", "webp", "WEBP"};
    private String[] videosuffixs = {"mp4", "MP4"};
    private String[] musicssuffixs = {"mp3", "MP3"};

    @Override
    public Map<String, Object> getAllUncategorized() throws Exception {
        Map<String, Object> res = new HashMap<>();

        File file = new File(unCategorizedPathLocal);
        Integer videoNum = 0;
        Integer imgNum = 0;
        Integer mangaNum = 0;
        Integer musicNum = 0;
        List<Video> videopath = new ArrayList<>();
        List<Img> imgpath = new ArrayList<>();
        List<Manga> mangapath = new ArrayList<>();
        List<String> musicpath = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 文件 / 文件夹 大小
                Long filesize = getFileOrFolderSize(files[i].getPath());
                // 文件名
                String fileNameNow = files[i].getPath().substring(files[i].getPath().lastIndexOf("\\")+1);
                if (files[i].isDirectory()) {
                    // == 漫画 ==
                    Manga manga = new Manga();
                    manga.setId(1);
                    File nowfile = new File(files[i].getPath());
                    File[] secondfile = nowfile.listFiles();
                    for (int j = 0; j < secondfile.length; j++) {
                        if (secondfile[j].isDirectory()) {
                            // 分章节

                        } else {
                            // 只有一章
                        }
                    }
                    ++mangaNum;
                } else {
                    // 文件路径
                    String filepath = files[i].getPath();
                    // .jpg
                    // String suffix = filepath.substring(filepath.lastIndexOf("."));
                    // jpg
                    String suffix=filepath.split("\\.")[filepath.split("\\.").length-1];
                    if (Arrays.asList(imgsuffixs).contains(suffix)) {
                        // == 图片 ==
                        Map<String, Integer> wah = getImgWAH(filepath);
                        Integer height = wah.get("height");
                        Integer width = wah.get("width");
                        Img img = new Img();
                        img.setHeight(height);
                        img.setWatchs(width);
                        img.setFilename(fileNameNow);
                        img.setPath(unCategorizedPathNet + fileNameNow);
                        img.setId(imgNum++);
                        imgpath.add(img);
                    } else if (Arrays.asList(videosuffixs).contains(suffix)) {
                        // == 视频 ==
                        Video video = new Video();
                        // base 64 封面
                        String frameBase64 = getFrameAsBase64(filepath, 5);
                        // 时长
                        String duration = getVideoDuration(filepath);
                        // 上传日期
                        long uploadTimestamp = getUploadTimestamp(filepath);
                        String upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(uploadTimestamp));
                        video.setCover(frameBase64);
                        video.setDuration(duration);
                        video.setTime(upTime);
                        video.setPath(unCategorizedPathNet + fileNameNow);
                        videopath.add(video);
                        video.setFilesize(filesize);
                        video.setFilename(fileNameNow);
                        video.setId(videoNum++);
                    } else if (Arrays.asList(musicssuffixs).contains(suffix)) {
                        // == 音乐 ==
                        musicpath.add((filepath));
                        ++musicNum;
                    }
                }
            }
        }
        res.put("mangas", mangapath);
        res.put("videos", videopath);
        res.put("musics", musicpath);
        res.put("imgs", imgpath);

        res.put("videonum", videoNum);
        res.put("imgnum", imgNum);
        res.put("manganum", mangaNum);
        res.put("musicnum", musicNum);
        return res;
    }
}
