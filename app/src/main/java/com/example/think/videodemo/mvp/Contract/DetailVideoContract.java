package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface DetailVideoContract {

    interface IView extends IBaseView{
        void showData(List<TestBean.StoriesBean> dataList);
    }
}
