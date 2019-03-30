package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class FunnyImageModel extends BaseApiImpl {

    public FunnyImageModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new FunnyImageModel(ApiService.INTEREST_IMAGE_URL).getRetrofit().create(ApiService.class);

    }
}
