package com.filecontroller;

import com.filecontroller.serve.FileService;
import com.filecontroller.serve.imp.FileServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Test
    void contextLoads() {
        Map<String, Object> res = new HashMap<>();
        res = fileServiceImp.getAllUncategorized();
        System.out.println(res);
    }

}
