package com.filecontroller.mapper;

import com.filecontroller.entity.DAO.FavoritesFloder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FFMapper {
    public List<FavoritesFloder> getOneType(String type);

    void addOneFolder(FavoritesFloder favoritesFloder);

    void updateOneFolder(FavoritesFloder favoritesFloder);
}
