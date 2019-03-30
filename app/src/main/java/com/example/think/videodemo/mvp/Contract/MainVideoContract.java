package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;
import java.util.Map;

public interface MainVideoContract {

    interface IView extends IBaseView{

        void showMainData(List<MainVideoBean.DetailVideo> detailVideos);

    }

    interface AdvView extends IBaseView{

        void showData( List<HotVideoBean.HotABean.itemBean> hotABeanList);

    }

}
