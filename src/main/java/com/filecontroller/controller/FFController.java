package com.filecontroller.controller;

import com.filecontroller.entity.DAO.FavoritesFloder;
import com.filecontroller.serve.FFService;
import com.filecontroller.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
