package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class CategoryModel extends BaseApiImpl {

    public CategoryModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new DetailVideoModel(ApiService.CATEGORY_URL).getRetrofit().create(ApiService.class);

    }
}
