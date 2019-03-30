package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.FunnyImageBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface FunnyImageContract {

    interface IView extends IBaseView {

        void showData(List<FunnyImageBean.Result.FunnyImageItem> dataList);
    }

}
