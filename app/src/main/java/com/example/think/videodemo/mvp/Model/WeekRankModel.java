package com.example.think.videodemo.mvp.Model;

import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class WeekRankModel extends BaseApiImpl {

    private static WeekRankModel weekRankModel = new WeekRankModel(ApiService.RANK_URL);

    public WeekRankModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {
        Log.d("Boomerr---test","WeekRankModel");
        return weekRankModel.getRetrofit().create(ApiService.class);
    }

}
