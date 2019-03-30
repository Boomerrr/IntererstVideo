package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface WelcomContract {

    interface WelView extends IBaseView {

        void showData(List<HotVideoBean.HotABean> dataList);

    }

}
