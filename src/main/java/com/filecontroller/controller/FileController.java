package com.filecontroller.controller;

import com.filecontroller.serve.FileService;
import com.filecontroller.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/getAllUncategorized")
    public Response getAllUncategorized() {
        try {
            Map<String, Object> res = new HashMap<>();
            res = fileService.getAllUncategorized();
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }
}
