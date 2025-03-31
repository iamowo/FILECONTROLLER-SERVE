package com.filecontroller.serve;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface FileService {
    Map<String, Object> getAllUncategorized() throws Exception;
}
