package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.HotVideoBean;
import com.example.think.videodemo.Bean.MainVideoBean;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface RankContract {

    interface HistIView extends IBaseView {

        void showData(List<RankBean.DetailRank> detailRanks);

    }

    interface WeekIView extends IBaseView {

        void showData(List<RankBean.DetailRank> detailRanks);

    }

    interface MonthIView extends IBaseView {

        void showData(List<RankBean.DetailRank> detailRanks);

    }

}
