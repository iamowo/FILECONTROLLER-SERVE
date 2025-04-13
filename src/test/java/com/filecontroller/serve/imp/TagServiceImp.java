package com.filecontroller.serve.imp;

import com.filecontroller.entity.DAO.Tag;
import com.filecontroller.mapper.TagMapper;
import com.filecontroller.serve.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> matchingTags(String tag) {
        return tagMapper.matchingTags(tag);
    }

    @Override
    public List<Tag> allTag() {
        return tagMapper.allTag();
    }

    @Override
    public void addThisTag(String tag) {
        return null;
    }
}
