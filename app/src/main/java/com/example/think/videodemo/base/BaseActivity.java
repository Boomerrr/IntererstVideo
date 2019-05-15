package com.example.think.videodemo.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 *
 *  Created by Boomerr 19/2/26
 *  email: yi.bochen@foxmail.com
 *
 * */

public abstract  class BaseActivity <V extends BasePresenter> extends Activity {

    protected V  mPresenter;

    public Activity mActivity;

    private ProgressDialog progressDialog = null;

    public void setContentView(@LayoutRes int layoutId){
        super.setContentView(layoutId);
        mActivity = this;
    }

    public void setContentView(View view){
        super.setContentView(view);
        mActivity = this;
    }

    public void setContentView(View view, ViewGroup.LayoutParams params){
        super.setContentView(view);
        mActivity = this;
    }


    private void createPresenter(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if(mPresenter !=  null){
            mPresenter.detachView();
        }
    }

    public void showLoading(){
        createPresenter();
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void hideLoading(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public void toast(String toastString){
        Toast.makeText(this,toastString,Toast.LENGTH_SHORT).show();
    }


}

