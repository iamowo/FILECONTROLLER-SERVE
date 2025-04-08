package com.filecontroller.controller;

import com.filecontroller.entity.Img;
import com.filecontroller.entity.Manga;
import com.filecontroller.entity.Music;
import com.filecontroller.entity.UP.OneCategorize;
import com.filecontroller.entity.Video;
import com.filecontroller.serve.FileService;
import com.filecontroller.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getUncategorizedVideo")
    public Response getUncategorizedVideo(@RequestParam Integer page, @RequestParam Integer num) {
        try {
            List<Video> res = fileService.getUncategorizedVideo(page, num);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @GetMapping("/getUncategorizedImg")
    public Response getUncategorizedImg(Integer page, Integer num) {
        try {
            List<Img> res = fileService.getUncategorizedImg(page, num);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @GetMapping("/getUncategorizedManga")
    public Response getUncategorizedManga(Integer page, Integer num) {
        try {
            List<Manga> res = fileService.getUncategorizedManga(page, num);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @GetMapping("/getUncategorizedMusic")
    public Response getUncategorizedMusic(Integer page, Integer num) {
        try {
            List<Music> res = fileService.getUncategorizedMusic(page, num);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @GetMapping("/getLength/{type}")
    public Response getLength (@PathVariable String type) {
        try {
            Integer res = fileService.getLength(type);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @PostMapping("/cateorizeFiles")
    public Response cateorizeFiles (@RequestBody OneCategorize oneCategorize) {
        try {
            fileService.cateorizeFiles(oneCategorize);
            return Response.success(200);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

}
