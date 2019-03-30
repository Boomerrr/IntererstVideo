package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class MainVideoModel  extends BaseApiImpl{

    public MainVideoModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new MainVideoModel(ApiService.MAIN_VIDEO_URL).getRetrofit().create(ApiService.class);

    }

}
