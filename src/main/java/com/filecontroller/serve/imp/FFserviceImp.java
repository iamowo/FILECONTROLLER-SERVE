package com.filecontroller.serve.imp;

import com.filecontroller.entity.DAO.FavoritesFloder;
import com.filecontroller.serve.FFService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FFserviceImp implements FFService {
    @Override
    public List<FavoritesFloder> getOneType(String type) {
        return null;
    }
}
