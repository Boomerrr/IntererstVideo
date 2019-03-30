package com.example.think.videodemo.base;
public interface IPresenter <V extends IBaseView>{

    void attachView(V mRootView);

    void detachView();
}

