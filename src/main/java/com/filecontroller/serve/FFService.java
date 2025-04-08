package com.filecontroller.serve;

import com.filecontroller.entity.DAO.FavoritesFloder;

import java.util.List;

public interface FFService {
    List<FavoritesFloder> getOneType(String type);

    void addOneFolder(FavoritesFloder favoritesFloder);

    void updateOneFolder(FavoritesFloder favoritesFloder);
}
