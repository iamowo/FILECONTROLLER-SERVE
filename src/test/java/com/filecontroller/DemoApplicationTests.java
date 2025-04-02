package com.filecontroller;

import com.filecontroller.entity.Video;
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
	void contextLoads() throws Exception {
		Map<String, Object> res = new HashMap<>();
		res = fileServiceImp.getAllUncategorized();
		System.out.println(res);
	}

	@Test
	void contextLoads2() throws Exception {
		Video v = new Video();
		v.setDuration("2233");
		System.out.println(v);
	}

}
