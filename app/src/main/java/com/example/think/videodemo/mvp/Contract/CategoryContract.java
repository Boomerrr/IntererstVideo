package com.example.think.videodemo.mvp.Contract;

import com.example.think.videodemo.Bean.CategoryItem;
import com.example.think.videodemo.Bean.TestBean;
import com.example.think.videodemo.base.IBaseView;

import java.util.List;

public interface CategoryContract {

    interface IView extends IBaseView {

        void showData(List<CategoryItem> dataList);
    }

}
