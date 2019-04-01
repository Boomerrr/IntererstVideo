package com.example.think.videodemo.mvp.Model;

import android.support.annotation.MainThread;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class JokeModel extends BaseApiImpl {

    public JokeModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {

        return new JokeModel(ApiService.INTEREST_JOKE_URL).getRetrofit().create(ApiService.class);

    }
}
