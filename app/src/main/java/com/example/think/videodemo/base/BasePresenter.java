package com.example.think.videodemo.base;

public abstract class BasePresenter<V extends IBaseView> {

    private V IView;

    void attachView(V IView){
        this.IView = IView;
    }

    void detachView(){
        IView = null;
    }
}

