package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.Bean.RankBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface CategoryActiContract {

    interface IView extends IBaseView {

        void showData(List<RankBean.DetailRank> dataList);
    }

}
