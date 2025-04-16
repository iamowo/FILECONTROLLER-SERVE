package com.filecontroller;

import com.filecontroller.entity.Video;
import com.filecontroller.serve.FileService;
import com.filecontroller.serve.imp.FileServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Test
    void contextLoads() throws Exception {
        List<Integer> a = Arrays.asList(1, 2, 3);
        a.forEach((n) -> System.out.println(a));
    }

    @Test
    void contextLoads2() throws Exception {
        Video v = new Video();
    }

}
