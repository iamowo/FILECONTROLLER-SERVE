package com.filecontroller.serve;

import com.filecontroller.entity.DAO.Tag;

import java.util.List;

public interface TagService {
    List<Tag> matchingTags(String tag);

    List<Tag> allTag();
}
