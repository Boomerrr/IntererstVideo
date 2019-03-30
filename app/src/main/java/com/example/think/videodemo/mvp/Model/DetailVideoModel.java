package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class DetailVideoModel extends BaseApiImpl {

    public DetailVideoModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new DetailVideoModel(ApiService.TEST_URL).getRetrofit().create(ApiService.class);

    }

}
