package com.example.think.videodemo.base;

import android.os.Bundle;
import android.widget.Toast;

/**
 *
 *  Created by Boomerr 19/2/26
 *
 *
 * */

public abstract  class MvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView{

    protected P mPresenter;

    protected void onCreate(Bundle savedInstanceState){
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    protected void onDestroy(){
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoad() {
        showLoading();
    }

    @Override
    public void dismissLoad() {
        hideLoading();
    }

    @Override
    public void showError() {
        toast("系统出错");
    }
}

