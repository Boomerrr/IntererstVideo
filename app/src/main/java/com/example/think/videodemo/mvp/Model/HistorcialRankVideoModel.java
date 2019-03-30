package com.example.think.videodemo.mvp.Model;

import android.util.Log;

import com.example.think.videodemo.Api.ApiService;
import com.example.think.videodemo.Api.BaseApiImpl;

public class HistorcialRankVideoModel extends BaseApiImpl{

    private static HistorcialRankVideoModel rankVideoModel = new HistorcialRankVideoModel(ApiService.RANK_URL);

    public HistorcialRankVideoModel(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {
        Log.d("Boomerr---test","HistorcialRankVideoModel");
        return rankVideoModel.getRetrofit().create(ApiService.class);
    }

}
