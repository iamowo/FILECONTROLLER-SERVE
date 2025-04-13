package com.filecontroller.serve.imp;

import com.filecontroller.entity.DAO.Tag;
import com.filecontroller.mapper.TagMapper;
import com.filecontroller.serve.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServicImp implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> matchingTags(String tag) {
        // 搜索时 like
        return tagMapper.matchingTags(tag);
    }

    @Override
    public List<Tag> allTag() {
        return tagMapper.allTag();
    }

    @Override
    public void addThisTag(String tag) {
        // tag 有唯一索引 ，存在会或略
        tagMapper.addTag(tag);
    }
}
