package com.filecontroller.controller;

import com.filecontroller.entity.DAO.Tag;
import com.filecontroller.entity.Video;
import com.filecontroller.serve.TagService;
import com.filecontroller.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/matchingTags/{tag}")
    public Response matchingTags(@PathVariable String tag) {
        try {
            List<Tag> res = tagService.matchingTags(tag);
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }

    @GetMapping("/allTag")
    public Response allTag() {
        try {
            List<Tag> res = tagService.allTag();
            return Response.success(res);
        } catch (Exception e) {
            return Response.failure(500, "error: " + e);
        }
    }
}
