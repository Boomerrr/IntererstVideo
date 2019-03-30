package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.JokeBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface JokeContract {

    interface IView extends IBaseView {

        void showData(List<JokeBean.Result.JokeItem> dataList);
    }

}
