package com.filecontroller.controller;

import com.filecontroller.entity.DAO.FavoritesFloder;
import com.filecontroller.serve.FFService;
import com.filecontroller.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ff")
public class FFController {
    @Autowired
    private FFService ffService;
    @GetMapping("/getOneType/{type}")
    public Response getOneType (@PathVariable String type) {
        try {
            List<FavoritesFloder> res = ffService.getOneType(type);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @PostMapping("/addOneFolder")
    public Response addOneFolder (@RequestBody FavoritesFloder favoritesFloder) {
        try {
            ffService.addOneFolder(favoritesFloder);
            return Response.success(200);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }
}
