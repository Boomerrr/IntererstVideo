package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class CategoryActiModel extends BaseApiImpl {

    public CategoryActiModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new DetailVideoModel(ApiService.SEARCH_URL).getRetrofit().create(ApiService.class);

    }
}
