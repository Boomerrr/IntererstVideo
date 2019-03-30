package com.example.think.videodemo.mvp.Model;

import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class MonthRankModel extends BaseApiImpl{

    private static MonthRankModel rankVideoModel = new MonthRankModel(ApiService.RANK_URL);

    public MonthRankModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {
        Log.d("Boomerr---test","MonthRankModel");
        return rankVideoModel.getRetrofit().create(ApiService.class);
    }

}
