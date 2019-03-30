package com.example.think.videodemo.mvp.Model;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class MainVideoAdverseModel extends BaseApiImpl{

    private static MainVideoAdverseModel mainVideoModel = new MainVideoAdverseModel(ApiService.HOT_VIDEO_URL);

    public MainVideoAdverseModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return mainVideoModel.getRetrofit().create(ApiService.class);

    }


}
