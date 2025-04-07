package com.filecontroller.mapper;

import com.filecontroller.entity.DAO.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    public List<Tag> allTag();

    List<Tag> matchingTags(String tag);
}
