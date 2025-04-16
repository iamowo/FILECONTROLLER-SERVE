package com.filecontroller.serve.imp;

import com.filecontroller.entity.*;
import com.filecontroller.entity.DAO.Resource;
import com.filecontroller.entity.UP.OneCategorize;
import com.filecontroller.mapper.FileMapper;
import com.filecontroller.mapper.TagMapper;
import com.filecontroller.serve.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.filecontroller.utils.DealFiles.*;
import static com.filecontroller.utils.DealFiles.getFileOrFolderSize;

@Service
public class FileServiceImp implements FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private TagMapper tagMapper;

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
    public List<Video> getUncategorizedVideo(Integer page, Integer num) throws Exception {
        File file = new File(unCategorizedPathLocal + "video/");
        Integer videoNum = 0;
        List<Video> res = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = page * num; i < (page + 1) * num; i++) {
                if (i >= files.length) {
                    break;
                }
                // 文件大小
                Long filesize = getFileOrFolderSize(files[i].getPath());
                // 文件路径
                String filePath = files[i].getPath();
                // 文件名
                String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
                // 后缀
                String suffix=filePath.split("\\.")[filePath.split("\\.").length-1];
                // 判断是都是mp4文件
                if (Arrays.asList(videosuffixs).contains(suffix)) {
                    Video video = new Video();
                    // base 64 封面
                    String frameBase64 = getFrameAsBase64(filePath, 5);
                    // 时长
                    String duration = getVideoDuration(filePath);
                    // 上传日期
                    long uploadTimestamp = getUploadTimestamp(filePath);
                    String upTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(uploadTimestamp));

                    video.setCover(frameBase64);
                    video.setDuration(duration);
                    video.setTime(upTime);
                    video.setFilesize(filesize);
                    video.setFilename(fileName);
                    video.setId(videoNum++);
                    video.setPath(unCategorizedPathNet + "video/" + fileName);

                    res.add(video);
                }
            }
        }
        return res;
    }

    @Override
    public List<Img> getUncategorizedImg(Integer page, Integer num) throws Exception {
        File file = new File(unCategorizedPathLocal + "img/");
        Integer imgNum = 0;
        List<Img> res = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 文件大小
                Long filesize = getFileOrFolderSize(files[i].getPath());
                // 文件路径
                String filePath = files[i].getPath();
                // 文件名
                String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
                // 后缀
                String suffix=filePath.split("\\.")[filePath.split("\\.").length-1];
                if (Arrays.asList(imgsuffixs).contains(suffix)) {
                    Img img = new Img();
                    Map<String, Integer> wah = getImgWAH(filePath);
                    Integer height = wah.get("height");
                    Integer width = wah.get("width");

                    img.setHeight(height);
                    img.setWatchs(width);
                    img.setFilename(fileName);
                    img.setPath(unCategorizedPathNet + "img/" + fileName);
                    img.setId(imgNum++);

                    res.add(img);
                }
            }
        }
        return res;
    }

    @Override
    public List<Manga> getUncategorizedManga(Integer page, Integer num) throws Exception {
        File file = new File(unCategorizedPathLocal + "manga/");
        Integer mangaNum = 0;
        List<Manga> res = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                Manga manga = new Manga();
                if (files[i].isDirectory()) {
                    manga.setId(mangaNum++);
                    // 文件大小
                    Long filesize = getFileOrFolderSize(files[i].getPath());
                    // 文件路径
                    String filePath = files[i].getPath();
                    // 文件名
                    String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
                    File[] sonChapters = files[i].listFiles();
                    List<MangaChapter> chapters = new ArrayList<>();
                    for (int j = 0; j < sonChapters.length; j++) {
                        // 一个章节
                        MangaChapter mangaChapter = new MangaChapter();
                        String chapterPath = sonChapters[j].getPath();
                        // 图片名称
                        String chapterName = chapterPath.substring(chapterPath.lastIndexOf("\\")+1);
                        File[] imgfils = sonChapters[i].listFiles();
                        mangaChapter.setImgnum(imgfils.length);
                        List<String> imgPath = new ArrayList<>();
                        for (int k = 0; k < imgfils.length; k++) {
                            String fp = imgfils[k].getPath();
                            // 图片名称
                            String fn = fp.substring(fp.lastIndexOf("\\")+1);
                            // 后缀
                            String suffix=fp.split("\\.")[fp.split("\\.").length-1];
                            String imgPathNet = unCategorizedPathNet + "manga/" + fileName + "/" + chapterName + "/" +fn;
                            if (i == 0 && k == 0) {
                                // 第一张第一个图片作为封面
                                manga.setCoverpath(imgPathNet);
                            }
                            imgPath.add(imgPathNet);
//                            if (Arrays.asList(imgsuffixs).contains(suffix)) {
//                                imgPath.add(imgPathNet);
//                            }
                        }
                        mangaChapter.setImgpath(imgPath);
                        mangaChapter.setFilesize(getFileOrFolderSize(imgfils[j].getPath()));
                        chapters.add(mangaChapter);
                    }
                    manga.setMangaChapter(chapters);
                    manga.setChapters(sonChapters.length);
                    manga.setFilename(fileName);
                    manga.setFilesize(filesize);
                    long uploadTimestamp = getUploadTimestamp(filePath);
                    manga.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(uploadTimestamp)));
                    manga.setPath(unCategorizedPathNet + "manga/" + fileName);
                }
                res.add(manga);
            }
        }
        return res;
    }

    @Override
    public List<Music> getUncategorizedMusic(Integer page, Integer num) throws Exception {
        Integer musicNum = 0;
        return List.of();
    }

    @Override
    public Integer getLength(String type) {
        File f = new File(unCategorizedPathLocal + type + "/");
        Integer res = f.listFiles().length;
        // 获取目标文件或文件夹个数
        return res;
    }

    @Override
    public void cateorizeFiles(OneCategorize oneCategorize) {
        // 添加tag
        for (int i = 0; i < oneCategorize.getTags().size(); i++) {
            tagMapper.addTag(oneCategorize.getTags().get(i));
        }

        // 资源信息
        Resource resource = new Resource();
        resource.setTitle(oneCategorize.getTitle());
        resource.setIntro(oneCategorize.getIntro());
        Integer fid = fileMapper.addOneResource(resource);
        // 添加信息
    }

    @Override
    public MangaChapter getOneMangaChapter(Integer id, String filepath) throws Exception {
        MangaChapter mangaChapter = new MangaChapter();
        if (id != -1) {
            // 未分类
            File file = new File(unCategorizedPathLocal + filepath);
            File[] imgFiles = file.listFiles();
            mangaChapter.setImgnum(imgFiles.length);
            List<String> imgpaths = new ArrayList<>();
            for (int i = 0; i < imgFiles.length; i++) {
                String fp = imgFiles[i].getPath();
                // 图片名称
                String fn = fp.substring(fp.lastIndexOf("\\")+1);
                // 后缀
                String suffix=fp.split("\\.")[fp.split("\\.").length-1];
                String imgPathNet = unCategorizedPathNet + "manga/" + filepath + "/" +fn;
                imgpaths.add(imgPathNet);
            }
            mangaChapter.setImgpath(imgpaths);
            mangaChapter.setFilesize(getFileOrFolderSize(file.getPath()));
        } else {

        }
        return mangaChapter;
    }
}