package com.filecontroller.mapper;

import com.filecontroller.entity.DAO.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    Integer addOneResource(Resource resource);
}
